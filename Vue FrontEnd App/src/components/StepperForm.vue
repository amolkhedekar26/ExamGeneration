<template>
  <div class="container">
    <article>
      <header>
        <div class="progress flex">
          <div
            class="progress-step"
            :class="{ active: index === activeStep }"
            v-for="(step, index) in formSteps"
            :key="'step' + index"
          >
            {{ index + 1 }}
          </div>
        </div>
      </header>
      <section :class="animation">
        <h1>Create Exam</h1>
        <form action="" method="post">
          <!-- <h2 class="input-fields" v-if="formSteps[activeStep] != undefined">
            {{ formSteps[activeStep].title }}
          </h2> -->
          <div class="input-fields" v-if="formSteps[activeStep] != undefined">
            <div
              class="input-container"
              v-for="(field, index) in formSteps[activeStep].fields"
              v-bind:key="index"
            >
              <label class="input-label">{{ field.label }}</label>
              <v-select
                v-model="selected[activeStep][index]"
                :options="suggestionsList[activeStep][index]"
                class="form-control"
                :key="'field' + index"
                v-if="field.inputType === 'select'"
              />
              <input
                class="form-control"
                type="number"
                v-model="selected[activeStep][index]"
                :key="'field' + index"
                v-if="field.inputType === 'input'"
              />
              <VueDatePicker
                class="form-control"
                v-model="selected[activeStep][index]"
                :key="'field' + index"
                v-if="field.inputType === 'date'"
              />
              <!-- <label class="input-label">{{ field.label }}</label> -->
            </div>
          </div>
          <div class="exam-summary" v-if="formSteps[activeStep] == undefined">
            <h3 class="text-center">Summary</h3>
            <ul>
              <!-- loop through all the `form` properties and show their values -->
              <li v-for="(item, k) in selected" v-bind:key="k + 'key'">
                <!-- <strong>{{ k }}:</strong>  -->
                <p v-for="(elt, idx) in item" v-bind:key="idx">
                  <span class="summary-field-name">
                    {{ formSteps[k].fields[idx]["fieldName"] }} :</span
                  >
                  {{ elt }}
                </p>
              </li>
            </ul>
          </div>
          <div class="actions">
            <button
              v-if="activeStep > 0 && activeStep <= formSteps.length"
              @click="goPrevious"
              class="btn-previous"
            >
              <span>
                <i class="fa fa-chevron-left" aria-hidden="true"></i>
              </span>
              prevoius
            </button>
            <button
              v-if="activeStep < formSteps.length - 1"
              @click="goNext"
              class="btn-next"
            >
              next
              <span>
                <i class="fa fa-chevron-right" aria-hidden="true"></i>
              </span>
            </button>
            <button
              v-if="activeStep === formSteps.length - 1"
              @click="goDone"
              class="btn-done"
            >
              done
              <span>
                <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
              </span>
            </button>
            <button
              v-if="activeStep === formSteps.length"
              @click="submitForm"
              class="btn-submit"
            >
              Create Exam
              <span>
                <i class="fa fa-plus" aria-hidden="true"></i>
              </span>
            </button>
          </div>
        </form>
      </section>
    </article>
  </div>
</template>

<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import { VueDatePicker } from "@mathieustan/vue-datepicker";
import "@mathieustan/vue-datepicker/dist/vue-datepicker.min.css";

export default {
  name: "StepperForm",
  components: {
    vSelect,
    VueDatePicker,
  },
  data: () => {
    return {
      selected: {
        0: {
          0: "Science",
          1: "BSc",
          2: "CS",
          3: "SY",
          4: "Semester 1",
          5: "Core Java",
        },
        1: {
          0: new Date(),
          1: "Semester",
          2: 90,
          3: 100,
          4: 40,
          5: "Active",
        },
      },
      // formModels: {
      //     selectStream:'',
      //     selectCourse:'',
      //     selectDepartment:'',
      //     selectClass:'',
      //     selectSemester:'',
      //     selectSubject:'',
      // },
      suggestionsList: {
        0: {
          0: ["Science", "Commerce", "Arts"],
          1: ["BSc", "BCom", "BA", "MSc", "MCom", "MA"],
          2: [
            "CS",
            "IT",
            "Physics",
            "Zoology",
            "Botony",
            "Chemistry",
            "Mathematics",
          ],
          3: ["FY", "SY", "TY"],
          4: [
            "Semester 1",
            "Semester 2",
            "Semester 3",
            "Semester 4",
            "Semester 5",
            "Semester 6",
            "Semester 7",
            "Semester 8",
          ],
          5: [
            "Software Testing",
            "Core Java",
            "Machine Learning",
            "Soft Skills",
            "Discrete Mathematics",
          ],
        },
        1: {
          0: [
            "2022/02/22",
            "2022/02/23",
            "2022/02/24",
            "2022/02/25",
            "2022/02/26",
          ],
          1: ["Semester", "UT"],
          4: ["Active", "Inactive", "Finished"],
        },
      },
      streams: ["Science", "Commerce", "Arts"],
      activeStep: 0,
      animation: "animate-in",
      // formInputTitles:{
      //   0:[],
      //   1:[]
      // },
      formSteps: [
        {
          title: "Primary Details",
          fields: [
            {
              fieldName: "Stream",
              label: "Enter Stream (e.g. Science, Arts)",
              value: "",
              valid: true,
              inputType: "select",
            },
            {
              fieldName: "Course",
              label: "Enter Course (e.g. Bsc, BCom, MA)",
              value: "",
              valid: true,
              inputType: "select",
            },
            {
              fieldName: "Department",
              label: "Enter Department (e.g. CS, IT, Botony)",
              value: "",
              valid: true,
              inputType: "select",
            },
            {
              fieldName: "Class",
              label: "Enter Class (e.g. FY, SY, TY)",
              value: "",
              valid: true,
              inputType: "select",
            },
            {
              fieldName: "Semester",
              label: "Enter Semester (e.g. 3, 6)",
              value: "",
              valid: true,
              inputType: "select",
            },
            {
              fieldName: "Subject",
              label: "Enter Subject (e.g. Software Testing, Java Core)",
              value: "",
              valid: true,
              inputType: "select",
            },
          ],
        },
        {
          title: "Additional Details",
          fields: [
            {
              fieldName: "Date of exam",
              label: "Date of Exam (e.g. 2022-02-22)",
              value: "",
              valid: true,
              inputType: "date",
            },
            {
              fieldName: "Type of exam",
              label: "Type of exam (e.g. Semester, UT)",
              value: "",
              valid: true,
              inputType: "select",
            },
            {
              fieldName: "Duration of exam",
              label: "Duration (e.g. 90 min, 120 min)",
              value: "",
              valid: true,
              inputType: "input",
            },
            {
              fieldName: "Total marks",
              label: "Total marks (e.g. 75, 100)",
              value: "",
              valid: true,
              inputType: "input",
            },
            {
              fieldName: "Passing Marks",
              label: "Passing Marks (e.g. 40, 24)",
              value: "",
              valid: true,
              inputType: "input",
            },
            {
              fieldName: "Exam status",
              label: "Status of exam (e.g. Active, Finished, Inactive)",
              value: "",
              valid: true,
              inputType: "select",
            },
          ],
        },
      ],
    };
  },
  methods: {
    nextStep(last) {
      if (last) {
        this.activeStep += 1;
      } else {
        this.animation = "animate-out";
        setTimeout(() => {
          this.animation = "animate-in";
          this.activeStep += 1;
        }, 550);
      }
    },
    previousStep() {
      this.animation = "animate-out";
      setTimeout(() => {
        this.animation = "animate-in";
        this.activeStep -= 1;
      }, 550);
    },
    goNext(event) {
      event.preventDefault();
      if (this.checkFields()) {
        this.nextStep(false);
      } else {
        this.animation = "animate-wrong";
        setTimeout(() => {
          this.animation = "";
        }, 400);
      }
    },
    goPrevious(event) {
      event.preventDefault();
      this.previousStep();
    },
    goDone(event) {
      event.preventDefault();
      if (this.checkFields()) {
        console.log("Done");
        this.nextStep(true);
      } else {
        this.animation = "animate-wrong";
        setTimeout(() => {
          this.animation = "";
        }, 400);
      }
    },
    submitForm(event) {
      event.preventDefault();
      alert(Object.values(this.selected));
    },
    checkFields() {
      let valid = true;
      this.formSteps[this.activeStep].fields.forEach((field) => {
        if (field.value) {
          valid = false;
          field.valid = false;
        } else {
          field.valid = true;
        }
      });

      // if (valid) {
      //   this.nextStep();
      // } else {
      // this.animation = "animate-wrong";
      // setTimeout(() => {
      //   this.animation = "";
      // }, 400);
      // }

      if (valid) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Noto+Sans&display=swap");

$bg-primary: #0d6efd;
$bg-primary-faded: #dfe7f3;
$bg-success: #198754;
$bg-danger: #dc3545;

@mixin flexbox() {
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  @include flexbox();
  width: 100%;
  min-height: 100vh;
  font-family: "Noto Sans", sans-serif;
  // background: radial-gradient(#DF5C2E, #A43227);
}

article {
  display: flex;
  flex-direction: column;
  margin: 10px;
  width: calc(100% - 20px);
  // max-width: 720px;
  max-width: 80%;
  // min-height: 480px;
  perspective: 1000px;
  // border-radius: 8px !important;
  header {
    @include flexbox();
    width: auto;
    // height: 480px;
    background-color: #fff;
    border-bottom: 2px dotted $bg-primary;
    padding: 16px;
    background-color: $bg-primary-faded;
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2), 0 10px 10px rgba(0, 0, 0, 0.2);
  }

  .progress-step {
    @include flexbox();
    position: relative;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-left: 20px;
    color: #fff;
    background-color: $bg-success;
    font-weight: bold;

    &.active {
      background-color: $bg-primary;

      ~ .progress-step {
        color: #555;
        background-color: whitesmoke;
      }

      ~ .progress-step::before {
        background-color: $bg-primary;
      }
    }

    &:before {
      content: "";
      position: absolute;
      top: 1.3rem;
      left: -20px;
      width: 20px;
      height: 2px;
      background-color: $bg-success;
      z-index: 10;
    }

    &:first-child::before {
      display: none;
    }
  }

  section {
    @include flexbox();
    flex-direction: column;
    width: 100%;
    background-color: #fff;
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2), 0 10px 10px rgba(0, 0, 0, 0.2);
    padding: 24px;

    form {
      @include flexbox();
      flex-direction: column;
      // width: 100%;
    }

    h1 {
      font-size: 2rem;
      color: rgb(11, 11, 49);
      margin-bottom: 20;
      // padding: 20px;
    }
    h2 {
      font-size: 1.6rem;
      color: #555;
      margin: 0;
      padding: 20px;
    }

    .input-fields {
      @include flexbox();
      justify-content: left;
      // flex-direction: column;
      width: 100%;
      flex-wrap: wrap;
    }

    .input-container {
      position: relative;
      padding: 8px 20px 10px 20px;
      width: calc(100% - 40px);
      flex: 0 0 50%;
      input {
        position: relative;
        width: 100%;
        height: 30px;
        font-family: "Noto Sans", sans-serif;
        font-size: 1.2rem;
        outline: none;
        background: transparent;
        box-shadow: none;
        border: 1px solid rgba(66, 65, 65, 0.219);
        border-radius: 4px;
        padding: 4px 8px;

        &:focus {
          border: 2px solid $bg-primary;
        }
        &:valid + .input-label {
          top: 10px;
          left: 20px;
          font-size: 0.875rem;
          font-weight: bold;
          color: rgb(27, 32, 46);
        }

        &.wrong-input + .input-label {
          color: $bg-danger;
        }
      }
    }
    .form-control {
      margin-top: 8px;
    }
    .vd-wrapper {
      border: 1px solid rgba(66, 65, 65, 0.219);
      border-radius: 4px;
      padding: 4px 8px;
    }
    .input-label {
      // position: absolute;
      // top: 35px;
      // left: 40px;
      font-size: 1rem;
      pointer-events: none;
      color: rgba(0, 0, 0, 0.801);
      transition: 0.2s ease-in-out;
    }

    .actions {
      margin: 30px;
      width: 100%;
      // display: flex;
      // justify-content: space-between;
      padding: 0 1.5rem 0 1.45rem;

      button {
        font-family: "Noto Sans", sans-serif;
        outline: none;
        border: none;
        color: #fff;
        background-color: $bg-primary;
        font-size: 1.5rem;
        padding: 8px 54px;
        // margin: 0 32px 0 0;
        text-transform: capitalize;
        border-radius: 3px;
        cursor: pointer;
        display: flex;
        justify-content: space-around;
        align-items: center;
      }
      .btn-previous {
        float: left;
      }
      .btn-next,
      .btn-done,
      .btn-submit {
        float: right;
      }
    }
  }
}

.animate-in {
  transform-origin: left;
  animation: in 0.6s ease-in-out;
}

.animate-out {
  transform-origin: bottom left;
  animation: out 0.6s ease-in-out;
}

.animate-wrong {
  animation: wrong 0.4s ease-in-out;
}
.vs__dropdown-toggle {
  height: 50px !important;
  font-size: 1.2rem !important;
}

.exam-summary {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f5f5f5;
  border-radius: 8px;
  // margin-bottom: 16px;
  padding: 1rem 2rem;

  ul {
    width: 100%;
    display: flex;
    justify-content: space-around;
    // flex-wrap: wrap;
    // margin: 2rem;

    li {
      flex: 0 0 50%;
    }

    p {
      margin-top: 0.875rem;
      flex-basis: 0 0 50%;
    }
  }
}

.summary-field-name {
  font-size: 1.2rem;
  color: black;
  font-weight: bold;
}
.fa {
  font-size: 1.5rem;
  margin-left: 8px;
  margin-right: 8px;
}
@keyframes in {
  0% {
    opacity: 0;
    transform: rotateY(90deg);
  }
  100% {
    opacity: 1;
    transform: rotateY(0deg);
  }
}

@keyframes out {
  0% {
    transform: translateY(0px) rotate(0deg);
  }
  60% {
    transform: rotate(10deg);
  }
  100% {
    transform: translateY(1000px);
  }
}

@keyframes wrong {
  0% {
    transform: translateX(0);
  }
  20% {
    transform: translateX(40px);
  }
  40% {
    transform: translateX(20px);
  }
  60% {
    transform: translateX(40px);
  }
  80% {
    transform: translateX(20px);
  }
  100% {
    transform: translateX(0);
  }
}
@media screen and (max-width: 768px) {
  /* STYLES HERE */
  article {
    max-width: 100%;
    // overflow-y:auto ;
    margin-top: 100px;
  }
  .input-container {
    flex: none !important;
    width: 100%;
  }
}
</style>