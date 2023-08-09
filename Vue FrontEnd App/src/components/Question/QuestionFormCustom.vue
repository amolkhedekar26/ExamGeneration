<template>
  <div class="question-container">
    <div class="col-md-4"></div>
    <div class="row quesstion-search-div">
      <label for="inputSubject" class="col-md-4"> Select Subject </label>
      <v-select
        id="inputSubject"
        class="col-md-8"
        :options="['Core Java', 'Green Computing', 'Python', 'Ruby On Rails']"
        v-model.trim="subject"
      />
    </div>
    <div class="question-container row mt-5">
      <div class="question-form-container col-md-6 border p-2 rounded">
        <div class="question-form-header">
          <h5 class="fw-bold">Question Form</h5>
          <input
            type="text"
            class="form-control"
            v-model="marks"
            placeholder="2"
          />
        </div>
        <textarea
          name="inputQuestionText"
          id="inputQuestionText"
          class="form-control mt-4"
          v-model="questionText"
          rows="4"
          placeholder="Type a question ..."
        ></textarea>
        <div class="option-container" v-if="questionText">
          <p class="text-muted p-2 fst-italic">
            <i class="fa fa-info-circle"></i>
            <span>
              Please select the correct answer from the options below.
            </span>
          </p>
          <ul id="question-options" class="mt-2">
            <li v-for="(option, index) in optionsList" :key="index">
              <input
                type="radio"
                :id="'option-' + index"
                class="mt-3"
                :value="option"
                name="inputQuestionOptions"
                v-model="correctOption"
              />
              <label :for="'option-' + index"></label>
              <input
                type="text"
                :value="option"
                class="px-2 border border-1"
                @input="editOption($event, index)"
              />
              <button
                class="btn btn-delete-option text-danger"
                @click="deleteOption($event, index)"
                :id="index"
              >
                <span>
                  <i class="fa fa-minus-circle" aria-hidden="true"></i>
                </span>
              </button>
              <!-- <input
                type="text"
                class="col-md-8 mx-4"
                v-model="optionsModels[index]"
                v-if="optionsEditClicked[index]"
              /> -->
            </li>
          </ul>
          <div class="add-more-options-div">
            <div
              class="add-option-div row mt-4 mb-4 mx-2"
              v-if="addOptionClicked"
            >
              <input
                type="text"
                class="col-md-8 px-2"
                v-model="newOption"
                placeholder="Enter option here"
              />
              <button
                class="btn btn-primary mx-1 col-3"
                @click="addOptionToList"
              >
                Add
              </button>
            </div>
          </div>
          <button
            class="btn text-decoration-underline text-primary"
            @click="toggleAddMoreOptions"
          >
            Add Option
          </button>
        </div>
        <div class="question-form-footer mt-4">
          <button
            class="btn btn-primary"
            @click="addQuestion"
            :disabled="!questionText || !correctOption"
          >
            Add Question
          </button>
        </div>
      </div>
      <div class="question-preview-container col-md-6 border p-2 rounded">
        <h5 class="fw-bold">Preview</h5>
        <div class="question-text-preview mt-4">
          <p v-if="!questionText" class="text-danger">
            Sorry ! You haven't written text yet
          </p>
          <p v-if="questionText">{{ questionText }}</p>
          <ul id="question-preview-options" v-if="questionText">
            <li v-for="(option, index) in optionsList" :key="index">
              {{ index + 1 }}. {{ option }}
              <!-- <input
                type="radio"
                :id="'option-' + index"
                :value="option"
                v-model="selectedOption"
              />
              <label :for="'option-' + index">{{ option }}</label> -->
            </li>
          </ul>
          <div class="correct-option-div mt-4" v-if="questionText">
            <h6 class="fw-bold">
              Correct Option :
              <span v-if="correctOption"> {{ correctOption }} </span>
              <span v-if="!correctOption">Not selected</span>
            </h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import { TYPE } from "vue-toastification";
import axios from "axios";
export default {
  name: "QuestionFormCustom",
  components: {
    vSelect,
  },
  data: () => {
    return {
      subject: "Core Java",
      questionText: "",
      marks: "2",
      optionsList: ["Option 1", "Option 2"],
      optionsModels: {},
      newOption: "",
      correctOption: "",
      addOptionClicked: false,
      optionsEditClicked: {},
    };
  },
  methods: {
    addQuestion() {
      const question = {
        subject: this.subject,
        questionText: this.questionText,
        options: this.optionsList,
        correctOption: this.correctOption,
      };
      // console.log(question);
      const obj = {
        squestionText: this.questionText,
        scategory: "MCQ",
        stag: "CUSTOM",
        jmarks: this.marks,
      };
      console.log(obj);
      axios
        .post("http://localhost:8080/TheExamAPI_war/api/tquestions", {
          squestionText: this.questionText,
          scategory: "MCQ",
          stag: "CUSTOM",
          jmarks: this.marks,
        })
        .then((response) => {
          console.log(response.data);
          const payload = {
            options: [],
          };
          payload["uidQuestion"] = response.data.uidQuestion;
          question.options.forEach((option) => {
            payload["options"].push({ scontent: option });
          });
          console.log(payload);

          axios
            .post(
              "http://localhost:8080/TheExamAPI_war/api/tmcqoptions/save-all",
              payload
            )
            .then((response) => {
              console.log(response.data);
            });

          this.$toast.success("Question added successfully", "Success", {
            type: TYPE.SUCCESS,
          });
        })
        .catch((error) => {
          console.log(error);
          this.$toast.error("Error adding question", "Error", {
            type: TYPE.ERROR,
          });
        });
      // this.$toast("Question added successfully", {
      //   type: TYPE.SUCCESS,
      //   timeout: 2000,
      // });
    },
    toggleAddMoreOptions() {
      this.addOptionClicked = !this.addOptionClicked;
    },
    addOptionToList() {
      this.optionsList.push(this.newOption);
      this.newOption = "";
      this.toggleAddMoreOptions();
    },
    editOption(event, index) {
      this.optionsList.splice(index, 1);
      this.optionsList.splice(index, 0, event.target.value);
    },
    deleteOption(event, index) {
      this.optionsList.splice(index, 1);
    },
  },
  created() {
    this.$emit("pageTitle", "Add Question to Bank");
  },
};
</script>

<style lang="scss" scoped>
.question-preview-container {
  background-color: rgba(215, 226, 250, 0.445);
}
.question-form-container textarea {
  resize: none;
}
ul {
  list-style: none;
}
ul li label {
  margin-left: 8px !important;
}
.question-form-header {
  display: flex;
  justify-content: space-between;
}
.question-form-header input {
  width: 20%;
  // padding: 0 40px;
}
</style>