<template>
  <div class="streams-main">
    <div class="streams-search-div">
      <!-- <auto-complete-input :dataList="exams" v-model="exam" /> -->
      <div class="row">
        <label for="inputexam" class="col-md-4">Select Exam</label>
        <v-select
          id="inputexam"
          class="auto-complete col-md-8"
          :value="exam"
          @input="setExamSelected"
          :options="examList"
          label="stitle"
          :reduce="(stitle) => stitle.uidExam"
        />
      </div>
      <input
        type="text"
        class="form-control mt-3"
        placeholder="Search a quesion here"
        v-model="searchQuery"
        v-if="!showExamPreview"
      />
    </div>
    <div class="streams-add-div" v-if="!showExamPreview">
      <input
        type="checkbox"
        name="select-all"
        id="select-all"
        v-model="allSelected"
        @change="selectAll"
      />
      Select All
      <button class="btn btn-primary">
        <router-link
          class="text-white text-decoration-none"
          :to="{ name: 'addQuestionToBank', params: { tag: 'CUSTOM' } }"
          >Add Custom Question</router-link
        >
      </button>
    </div>
    <div class="streams-list-div" v-if="!showExamPreview">
      <!-- <accor-dian /> -->
      <vue-confirm-dialog></vue-confirm-dialog>
      <!-- <modal class="add-modal" name="my-first-modal">
        <div class="modal-container">
          <div class="header-div">
            <h1>Add new Stream</h1>
            <button @click="closeModal">❌</button>
          </div>
          <div class="input-div">
            <div class="input-container">
              <label class="input-label">Enter title of stream</label>
              <input type="text" v-model="title" name="title" />
            </div>
            <div class="input-container">
              <label class="input-label">Enter title of stream</label>
              <input type="text" v-model="title" name="title" />
            </div>
            <div class="input-container">
              <label class="input-label">Enter title of stream</label>
              <input type="text" v-model="title" name="title" />
            </div>
            <div class="input-container">
              <label class="input-label">Enter details of stream</label>
              <textarea v-model="details" name="details" rows="10" />
            </div>
          </div>
          <div class="action-div">
            <button type="submit">Submit</button>
          </div>
        </div>
      </modal> -->
      <!-- <div class="list-title">

      </div>
      <div class="list-table">
        <div class="card"></div>
        <div class="card"></div>
        <div class="card"></div>
      </div> -->
      <div class="streams-table">
        <div class="table-row header blue">
          <div class="cell">#</div>
          <div class="cell"></div>
          <div class="cell">Question Details</div>
          <div class="cell">Marks</div>
        </div>

        <div class="table-row" v-for="(q, index) in resultQuery" :key="q.id">
          <div class="cell" data-title="id">{{ index + 1 }}</div>
          <input
            type="checkbox"
            v-model="checkedQuestions"
            :checked="checkedQuestions.indexOf(+q.id) > -1"
            :value="q.id"
          />
          <div class="cell col-md-8" data-title="title">
            <!-- <a href="#">{{ q.question }}</a> -->
            <!-- <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button
                  class="accordion-button"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#collapseOne"
                  aria-expanded="true"
                  aria-controls="collapseOne"
                >
                  {{ q.question }}
                </button>
              </h2>
              <div
                id="collapseOne"
                class="accordion-collapse collapse show"
                aria-labelledby="headingOne"
                data-bs-parent="#accordionExample"
              >
                <div class="accordion-body">
                  {{ q.question }}
                  <ul style="list-style-type: none">
                    <li v-for="op in q.optionsList" :key="op.id">
                      {{ op.id }}.
                      {{ op.option }}
                    </li>
                  </ul>
                </div>
              </div>
            </div> -->
            <!-- <Collapsible
              class="example-collapsible"
              :isOpen="false"
              :openLabel="'+'"
            >
              <div slot="trigger">
                <div class="customTrigger">
                  <h5>{{ q.question }}</h5>
                </div>
              </div>

              <div slot="closedTrigger">
                <div class="customTrigger">
                  <h2>Custom closed trigger element</h2>
                </div>
              </div>

              <div id="example2">
                <p>Lorem ipsum dolor sit ...</p>
              </div>
            </Collapsible> -->
            <collapsible-component :question="q" />
          </div>
          <div class="cell" data-title="checkmark">
            <input
              type="text"
              class="col-md-12"
              :value="q.marks"
              @input="onMarksEdit($event, q.id)"
            />
          </div>
        </div>
        <!-- <p>
          {{ checkedQuestions }}
        </p> -->
        <button
          class="btn btn-success"
          @click="openExamPreview"
          style="
            position: fixed;
            bottom: 20px;
            right: 20px;
            padding: 8px 24px;
            z-index: 1000;
          "
        >
          Proceed
        </button>
        <!-- <div class="table-row">
          <div class="cell" data-title="id">2</div>
          <div class="cell" data-title="title"><a href="#">Commerce</a></div>
          <div class="cell" data-title="details">
            The main stream of all commerce faculties
          </div>
          <div class="cell">
            <span>
              <box-icon name="edit-alt"></box-icon>
            </span>
          </div>
          <div class="cell">
            <span>
              <box-icon name="trash"></box-icon>
            </span>
          </div>
        </div>

        <div class="table-row">
          <div class="cell" data-title="id">3</div>
          <div class="cell" data-title="title"><a href="#">Arts</a></div>
          <div class="cell" data-title="details">
            The main stream of all arts faculties
          </div>
          <div class="cell">
            <span>
              <box-icon name="edit-alt"></box-icon>
            </span>
          </div>
          <div class="cell">
            <span>
              <box-icon name="trash"></box-icon>
            </span>
          </div>
        </div> -->
      </div>
    </div>
    <div v-if="showExamPreview">
      <vue-confirm-dialog></vue-confirm-dialog>
      <button class="btn" @click="openExamPreview">
        <i class="fa fa-arrow-left" aria-hidden="true"></i>
      </button>
      <ul style="list-style-type: none">
        <li
          v-for="(selectedQuestion, idx) in selectedQuestions"
          :key="idx"
          class="row"
        >
          <span class="col-1">{{ idx + 1 }} </span>
          <collapsible-component
            class="col-10"
            :question="selectedQuestion"
            :isOpen="false"
          />
        </li>
      </ul>
      <button
        class="btn btn-success"
        @click="collectSelectedQuesions"
        style="
          position: fixed;
          bottom: 20px;
          right: 20px;
          padding: 8px 24px;
          z-index: 1000;
        "
      >
        Confirm
      </button>
    </div>
  </div>
</template>

<script>
// import AutoCompleteInput from "../AutoCompleteInput/AutoCompleteInput.vue";
// import axios from "axios";
// import AccorDian from "../AccorDian.vue";
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import "vue-collapsible-component/lib/vue-collapsible.css";
import CollapsibleComponent from "../CustomAccordian/CollapsibleComponent.vue";
import axios from "axios";
import { TYPE } from "vue-toastification";
export default {
  name: "ExamConfiguration",
  data: () => {
    return {
      questions: [],
      checkedQuestions: [],
      searchQuery: null,
      allSelected: false,
      questionsList: [],
      optionsList: [],
      exam: "",
      examList: [],
      showExamPreview: false,
      selectedQuestions: [],
    };
  },
  components: {
    // AutoCompleteInput,
    // AccorDian,
    vSelect,
    CollapsibleComponent,
  },
  computed: {
    resultQuery() {
      if (this.searchQuery) {
        return this.questions.filter((item) => {
          return this.searchQuery
            .toLowerCase()
            .split(" ")
            .every((v) => item.question.toLowerCase().includes(v));
        });
      } else {
        return this.questions;
      }
    },
  },
  mounted() {
    this.$emit("pageTitle", "Configure Examination");
    // fetch("http://localhost:8080/TheExamAPI_war/api/tstreams")
    //   .then((response) => response.json())
    //   .then((data) => {
    //     this.streams = data;
    //     const result = data.map((a) => a.stitle);
    //     this.streamsOptions = result;
    //   });
    // this.fetchStreams();
    this.fetchExams();
    this.fetchQuestions();
    // this.questions = [
    //   {
    //     id: 1,
    //     question: "What is the capital of India?",
    //     isExpand: true,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "New Delhi",
    //       },
    //       {
    //         id: 2,
    //         option: "Mumbai",
    //       },
    //       {
    //         id: 3,
    //         option: "Chennai",
    //       },
    //       {
    //         id: 4,
    //         option: "Kolkata",
    //       },
    //     ],
    //     marks: 1,
    //   },
    //   {
    //     id: 2,
    //     question: "Where is the Taj Mahal?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Agra",
    //       },
    //       {
    //         id: 2,
    //         option: "Delhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Mumbai",
    //       },
    //       {
    //         id: 4,
    //         option: "Kolkata",
    //       },
    //     ],
    //     marks: 2,
    //   },
    //   {
    //     id: 3,
    //     question: "Who is the president of India?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 1,
    //   },
    //   {
    //     id: 4,
    //     question: "Who is the prime minister of India?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 2,
    //   },
    //   {
    //     id: 5,
    //     question: "Who is the president of USA?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 1,
    //   },
    //   {
    //     id: 6,
    //     question: "Who is the president of UK?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 2,
    //   },
    //   {
    //     id: 7,
    //     question: "Who is the president of Australia?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 1,
    //   },
    //   {
    //     id: 8,
    //     question: "Who is the president of Canada?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 2,
    //   },
    //   {
    //     id: 9,
    //     question: "Who is the president of Germany?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 1,
    //   },
    //   {
    //     id: 10,
    //     question: "Who is the president of Italy?",
    //     isExpand: false,
    //     optionsList: [
    //       {
    //         id: 1,
    //         option: "Narendra Modi",
    //       },
    //       {
    //         id: 2,
    //         option: "Rahul Gandhi",
    //       },
    //       {
    //         id: 3,
    //         option: "Sonia Gandhi",
    //       },
    //       {
    //         id: 4,
    //         option: "Manmohan Singh",
    //       },
    //     ],
    //     marks: 2,
    //   },
    // ];
  },
  methods: {
    fetchQuestions() {
      axios
        .get("http://localhost:8080/TheExamAPI_war/api/tquestions")
        .then((response) => {
          this.questionsList = response.data;
          this.$root.log(this.questionsList);
          // this.fetchMCQOptions();
          this.questionsList.forEach((question) => {
            const questionObj = {
              id: question.uidQuestion,
              question: question.squestionText,
              isExpand: false,
              optionsList: [],
              marks: question.jmarks,
            };
            this.fetchMCQOptions(question.uidQuestion, questionObj);
          });
        });
    },
    fetchMCQOptions(uidQuestion, questionObj) {
      axios
        .get(
          `http://localhost:8080/TheExamAPI_war/api/tmcqoptions/mcq-option-by-question?uidQuestion=${uidQuestion}`
        )
        .then((response) => {
          // this.optionsList = response.data;
          this.$root.log(response.data);
          const mcqOptions = response.data;
          mcqOptions.forEach((option) => {
            questionObj.optionsList.push({
              id: option.id,
              option: option.scontent,
            });
          });
          this.questions.push(questionObj);
        });
    },
    fetchExams() {
      axios
        .get("http://localhost:8080/TheExamAPI_war/api/texams")
        .then((response) => {
          this.examList = response.data;
          this.$root.log(this.examList);
        });
    },
    setExamSelected(value) {
      this.exam = value;
      console.log(this.exam);
    },
    deleteStream(event) {
      event.preventDefault();
      this.$confirm({
        message: `Are you sure want to delete?`,
        button: {
          no: "No",
          yes: "Yes",
        },
        /**
         * Callback Function
         * @param {Boolean} confirm
         */
        callback: (confirm) => {
          if (confirm) {
            // ... do something

            console.log("Delete API called");
          }
        },
      });
    },
    onMarksEdit(event, id) {
      console.log(event.target.value, id);
      this.questions.find((q) => q.id === id).marks = event.target.value;
      console.log(this.questions);
    },
    openExamPreview() {
      this.showExamPreview = !this.showExamPreview;
      const selectedQuestions = this.questions.filter(
        (q) => this.checkedQuestions.indexOf(q.id) > -1
      );
      this.selectedQuestions = selectedQuestions;
    },
    collectSelectedQuesions() {
      // const selectedQuestions = this.questions.filter(
      //   (q) => this.checkedQuestions.indexOf(q.id) > -1
      // );
      // this.selectedQuestions = selectedQuestions;
      const selectedQuestions = this.selectedQuestions;
      this.$confirm({
        message: `Do you really want to add these questions ?`,
        button: {
          no: "No",
          yes: "Yes",
        },
        /**
         * Callback Function
         * @param {Boolean} confirm
         */
        callback: (confirm) => {
          if (confirm) {
            this.$root.log(selectedQuestions);
            const payload = {
              uidExam: this.exam,
              questions: [],
            };
            selectedQuestions.forEach((q) => {
              payload["questions"].push({
                uidQuestion: q.id,
              });
            });
            console.log(payload);
            axios
              .post(
                "http://localhost:8080/TheExamAPI_war/api/texamquestionmapping/save-all",
                payload
              )
              .then((response) => {
                this.$root.log(response.data);
                this.$toast("Questions added to exam", {
                  type: TYPE.SUCCESS,
                  timeout: 4000,
                });
              });
          }
        },
      });
    },
    selectAll() {
      if (this.allSelected) {
        this.checkedQuestions = this.questions.map((q) => q.id);
      } else {
        this.checkedQuestions = [];
      }
    },
  },
};
</script>

<style lang="scss" scoped>
$bg-primary: #2980b9;
$bg-primary-faded: #dfe7f3;
$bg-primary-hover: #02409ee3;
$bg-primary-header: #297fb9d3;

a {
  text-decoration: none;
}
.streams-main {
  min-height: inherit;
  display: flex;
  flex-direction: column;
  max-width: 70%;
  margin: 0 auto;
  /* align-items: center; */
}
.streams-main h1 {
  text-align: start;
  font-size: 1.5rem;
  font-weight: bolder;
}
.streams-search-div {
  margin: 1rem 0;
  width: 100%;
}
.streams-add-div {
  button {
    text-align: center;
    padding: 8px 16px;
    text-decoration: none;
    font-size: 1rem;
    transition: all 0.3s;
    border-radius: 4px;
    background-color: $bg-primary;
    color: whitesmoke;
    // font-size: 1.2rem;
    font-weight: bold;
    float: right;
  }
  button:hover {
    background-color: $bg-primary-hover;
    color: #dfe7f3;
  }
}
.streams-list-div {
  margin: 1rem 0;
  // padding: 8px;
  border-radius: 4px;
  // background-color: purple  ;
  // min-height: 400px;
  max-height: 600px;
  font-size: 1.2rem;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  .card {
    background-color: rgba(221, 217, 217, 0.219);
    min-height: 80px;
    max-height: 50px;
    border-radius: 8px;
  }
  .card + .card {
    margin-top: 8px;
  }
}
.vm--overlay.vm--modal {
  background-color: red !important;
}
.modal-container {
  padding: 16px;
  // background-color: #f1f1f1;
  min-height: 100%;
}
.header-div {
  display: flex;
  justify-content: space-between;
}
.input-div {
  margin: 16px 0 16px 0;
}
.input-container {
  display: flex;
  flex-direction: column;
  margin-top: 16px;
}
.input-container input,
textarea {
  position: relative;
  width: 100%;
  height: 50px;
  font-family: "Noto Sans", sans-serif;
  font-size: 1.2rem;
  outline: none;
  background: #f1f1f1;
  box-shadow: none;
  border: 1px solid rgba(66, 65, 65, 0.219);
  border-radius: 4px;
  padding: 4px 8px;
}

.input-container textarea {
  position: relative;
  width: 100%;
  // height: 100px;
  font-family: "Noto Sans", sans-serif;
  font-size: 1.2rem;
  outline: none;
  background: #f1f1f1;
  box-shadow: none;
  border: 1px solid rgba(66, 65, 65, 0.219);
  border-radius: 4px;
  padding: 4px 8px;
}

@media screen and (max-width: 580px) {
  // body {
  //   font-size: 16px;
  //   line-height: 22px;
  // }
  .streams-main {
    max-width: 90%;
  }
}
.table-row button {
  outline: none;
  border: none;
}

@media screen and (max-width: 768px) {
  // body {
  //   font-size: 16px;
  //   line-height: 22px;
  // }
  .streams-main {
    max-width: 90%;
  }
}

.streams-table {
  // margin: 0 0 40px 0;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  display: table;
}
@media screen and (max-width: 580px) {
  .streams-table {
    display: block;
  }
}

.table-row {
  display: table-row;
  background: #f6f6f6;
}
.table-row a:hover {
  color: $bg-primary;
  font-weight: bold;
  transition: all 0.3s ease;
}
.table-row:nth-of-type(odd) {
  background: #e9e9e9;
}
.table-row.header {
  font-weight: 900;
  color: #ffffff;
  // background: #ea6153;
}
.table-row.blue {
  background: $bg-primary-header;
}
.cell span {
  cursor: pointer;
}
@media screen and (max-width: 580px) {
  .table-row {
    padding: 14px 0 7px;
    display: block;
  }
  .table-row.header {
    padding: 0;
    height: 6px;
  }
  .table-row.header .cell {
    display: none;
  }
  .table-row .cell {
    margin-bottom: 10px;
  }
  .table-row .cell:before {
    margin-bottom: 3px;
    content: attr(data-title);
    min-width: 98px;
    font-size: 10px;
    line-height: 10px;
    font-weight: bold;
    text-transform: uppercase;
    color: #969696;
    display: block;
  }
}

.cell {
  padding: 16px 12px;
  display: table-cell;
}

@media screen and (max-width: 580px) {
  .cell {
    padding: 2px 16px;
    display: block;
  }
}
</style>