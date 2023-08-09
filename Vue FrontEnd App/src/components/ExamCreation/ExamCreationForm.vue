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
      <section class="form-container g-3 needs-validation">
        <div class="row" v-if="activeStep == 0">
          <h1 class="fs-3">Create Exam</h1>
          <!-- <form action="" method="post">
              <div class="input-fields" v-if="formSteps[activeStep] != undefined">
                <div class="input-container">
                  <label class="input-label">Choose Stream</label>
                  <v-select
                    :value="selected[activeStep]['stream']"
                    @input="setSelected"
                    :options="suggestionsList[activeStep]['streamList']"
                    label="stitle"
                    :reduce="(stitle) => stitle.uidStream"
                    class="form-control"
                  />
                </div>
                <div class="input-container">
                  <label class="input-label">Choose Stream</label>
                  <v-select
                    :value="selected[activeStep]['stream']"
                    @input="setSelected"
                    :options="suggestionsList[activeStep]['streamList']"
                    label="stitle"
                    :reduce="(stitle) => stitle.uidStream"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="exam-summary" v-if="formSteps[activeStep] == undefined">
                <h3 class="text-center">Summary</h3>
                <ul>
                  <li v-for="(item, k) in selected" v-bind:key="k + 'key'">
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
                    <i class="fa fa-chevron-left fs-6" aria-hidden="true"></i>
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
                    <i class="fa fa-chevron-right fs-6" aria-hidden="true"></i>
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
            </form> -->
          <div class="row">
            <div class="input-container">
              <label for="inputStream" class="form-label">Select stream</label>
              <v-select
                id="inputStream"
                class="form-control auto-complete"
                :value="selected[activeStep]['stream']"
                @input="setStreamSelected"
                :options="suggestionsList[activeStep]['streamList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidStream"
              />
            </div>
            <div class="input-container">
              <label for="inputCourse" class="form-label">Select course</label>
              <v-select
                id="inputCourse"
                class="form-control auto-complete"
                :value="selected[activeStep]['course']"
                @input="setCourseSelected"
                :options="suggestionsList[activeStep]['courseList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidCourse"
              />
            </div>
          </div>
          <div class="row">
            <div class="input-container">
              <label for="inputDepartment" class="form-label"
                >Select Department</label
              >
              <v-select
                id="inputDepartment"
                class="form-control auto-complete"
                :value="selected[activeStep]['department']"
                @input="setDepartmentSelected"
                :options="suggestionsList[activeStep]['departmentList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidDepartment"
              />
            </div>
            <div class="input-container">
              <label for="inputClass" class="form-label">Select Class</label>
              <v-select
                id="inputClass"
                class="form-control auto-complete"
                :value="selected[activeStep]['className']"
                @input="setClassSelected"
                :options="suggestionsList[activeStep]['classNameList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidClass"
              />
            </div>
          </div>
          <div class="row">
            <div class="input-container">
              <label for="inputSemester" class="form-label"
                >Select Semester</label
              >
              <v-select
                id="inputSemester"
                class="form-control auto-complete"
                :value="selected[activeStep]['semester']"
                @input="setSemesterSelected"
                :options="suggestionsList[activeStep]['semesterList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidSemester"
              />
            </div>
            <div class="input-container">
              <label for="inputSubject" class="form-label"
                >Select subject</label
              >
              <v-select
                id="inputSubject"
                class="form-control auto-complete"
                :value="selected[activeStep]['subject']"
                @input="setSubjectSelected"
                :options="suggestionsList[activeStep]['subjectList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidSubject"
              />
            </div>
          </div>
        </div>
        <div class="row" v-if="activeStep == 1">
          <h1 class="fs-3">Create Exam</h1>
          <div class="row">
            <div class="input-container">
              <label for="inputDateOfExam" class="form-label"
                >Date of Exam</label
              >
              <VueDatePicker
                id="inputDateOfExam"
                class="form-control"
                v-model="dateOfExam"
              />
            </div>
            <div class="input-container">
              <label for="inputExamType" class="form-label">Exam Type</label>
              <v-select
                id="inputExamType"
                class="form-control auto-complete"
                v-model="examType"
                :options="suggestionsList[activeStep]['examTypeList']"
              />
            </div>
          </div>
          <div class="row">
            <div class="input-container">
              <label for="inputTotalMarks" class="form-label"
                >Total Marks</label
              >
              <!-- <v-select
                id="inputDepartment"
                class="form-control auto-complete"
                :value="selected[activeStep]['department']"
                @input="setDepartmentSelected"
                :options="suggestionsList[activeStep]['departmentList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidDepartment"
              /> -->
              <input
                id="inputTotalMarks"
                class="form-control"
                type="number"
                v-model="totalMarks"
              />
            </div>
            <div class="input-container">
              <label for="inputPassingMarks" class="form-label"
                >Passing marks</label
              >
              <!-- <v-select
                id="inputClass"
                class="form-control auto-complete"
                :value="selected[activeStep]['class']"
                @input="setClassSelected"
                :options="suggestionsList[activeStep]['classList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidClass"
              /> -->
              <input
                id="inputPassingMarks"
                class="form-control"
                type="number"
                v-model="passingMarks"
              />
            </div>
          </div>
          <div class="row">
            <div class="input-container">
              <label for="inputExamDuration" class="form-label"
                >Duration of Exam</label
              >
              <!-- <v-select
                id="inputSemester"
                class="form-control auto-complete"
                :value="selected[activeStep]['semester']"
                @input="setSemesterSelected"
                :options="suggestionsList[activeStep]['semesterList']"
                label="stitle"
                :reduce="(stitle) => stitle.uidSemester"
              /> -->
              <input
                id="inputExamDuration"
                class="form-control"
                type="number"
                v-model="examDuration"
              />
            </div>
            <div class="input-container">
              <label for="inputExamStatus" class="form-label"
                >Status of Exam</label
              >
              <v-select
                id="inputExamStatus"
                class="form-control auto-complete"
                v-model="examStatus"
                :options="suggestionsList[activeStep]['examStatusList']"
              />
            </div>
          </div>
        </div>
        <div class="exam-summary" v-if="formSteps[activeStep] == undefined">
          <h3 class="text-center">Summary</h3>
          <ul>
            <li v-for="(item, k) in selected" v-bind:key="k + 'key'">
              <p v-for="(elt, idx) in item" v-bind:key="idx">
                <span class="summary-field-name"> {{ idx }} :</span>
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
              <i class="fa fa-chevron-left fs-6" aria-hidden="true"></i>
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
              <i class="fa fa-chevron-right fs-6" aria-hidden="true"></i>
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
          </button>
        </div>
      </section>
    </article>
  </div>
</template>

<script>
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
import { VueDatePicker } from "@mathieustan/vue-datepicker";
import "@mathieustan/vue-datepicker/dist/vue-datepicker.min.css";
import axios from "axios";
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
          stream: "Science",
          course: "BSc",
          department: "CS",
          className: "SY",
          semester: "Semester 1",
          subject: "Core Java",
          departmentClassMapping: "",
          departmentClassMappSemesterMapping: "",
        },
        1: {
          dateOfExam: new Date(),
          examType: "SEMESTER",
          examDuration: 90,
          totalMarks: 100,
          passingMarks: 40,
          examStatus: "ACTIVE",
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
          streamList: [],
          courseList: [],
          departmentList: [],
          classNameList: [],
          semesterList: [],
          subjectList: [],
          departmentClassMapping: [],
          departmentClassMappSemesterList: [],
        },
        1: {
          0: [
            "2022/02/22",
            "2022/02/23",
            "2022/02/24",
            "2022/02/25",
            "2022/02/26",
          ],
          examTypeList: ["SEMESTER", "UT"],
          examStatusList: ["ACTIVE", "INACTIVE", "FINISHED"],
        },
      },
      streams: ["Science", "Commerce", "Arts"],
      departmentClassMappingList: [],
      departmentClassMappSemesterList: [],
      subjectList: [],

      subject: "",
      dateOfExam: new Date(),
      examType: "SEMESTER",
      examDuration: 90,
      totalMarks: 100,
      passingMarks: 40,
      examStatus: "ACTIVE",
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
  mounted() {
    this.$emit("pageTitle", "Create Exam");
    // fetch("http://localhost:8080/TheExamAPI_war/api/tstreams")
    //   .then((response) => response.json())
    //   .then((data) => {
    //     this.streams = data;
    //     const result = data.map((a) => a.stitle);
    //     this.streamsOptions = result;
    //   });
    this.fetchStreams();
  },
  methods: {
    setStreamSelected(value) {
      console.log(value);
      this.selected[this.activeStep]["stream"] = value;
      //   this.fetCoursesByStream(value);
      this.fetCoursesByStream(value);
    },
    setCourseSelected(value) {
      console.log(value);
      this.selected[this.activeStep]["course"] = value;
      this.fetchDepartmentByCourse(value);
    },
    setDepartmentSelected(value) {
      console.log(value);
      this.selected[this.activeStep]["department"] = value;
      this.fetchClassesByDepartment(value);
    },
    setClassSelected(value) {
      this.selected[this.activeStep]["className"] = value;
      this.$root.log(this.selected[this.activeStep]["className"]);
      const obj = this.departmentClassMappingList.filter((item) => {
        return (
          item["uidClassFk"]["uidClass"] ==
            this.selected[this.activeStep]["className"] &&
          item["uidDepartmentFk"]["uidDepartment"] ==
            this.selected[this.activeStep]["department"]
        );
      });
      // this.departmentClassMapping = obj["uidDepartmentClassMapping"];
      console.log("here is", obj);
      this.selected[this.activeStep]["departmentClassMapping"] =
        obj[0]["uidDepartmentClassMapping"];
      this.fetchSemesterByClass();
    },
    setSemesterSelected(value) {
      this.selected[this.activeStep]["semester"] = value;
      this.$root.log(this.selected[this.activeStep]["semester"]);
      const obj = this.departmentClassMappSemesterList.filter((item) => {
        return (
          item["uidSemesterFk"]["uidSemester"] ==
          this.selected[this.activeStep]["semester"]
        );
      });
      console.log("here is", obj);
      this.selected[this.activeStep]["departmentClassMappSemesterMapping"] =
        obj[0]["uidDepartmentClassMapSemesterMapping"];
      this.fetchSubjectsBySemester();
    },
    setSubjectSelected(value) {
      this.selected[this.activeStep]["subject"] = value;
      this.subject = value;
    },
    setExamTypeSelected(value) {
      console.log(value);
      this.selected[this.activeStep]["examType"] = value;
    },
    setExamStatusSelected(value) {
      console.log(value);
      this.selected[this.activeStep]["examStatus"] = value;
    },
    fetchStreams() {
      axios
        .get("http://localhost:8080/TheExamAPI_war/api/tstreams")
        .then((response) => {
          this.suggestionsList[this.activeStep]["streamList"] = response.data;
        });
    },
    fetCoursesByStream() {
      axios
        .get(
          `http://localhost:8080/TheExamAPI_war/api/tcourses/tcourses-by-stream?streamUid=${
            this.selected[this.activeStep]["stream"]
          }`
        )
        .then((response) => {
          this.suggestionsList[this.activeStep]["courseList"] = response.data;
          this.$root.log(this.suggestionsList[this.activeStep]["courseList"]);
        });
    },
    fetchDepartmentByCourse() {
      axios
        .get(
          `http://localhost:8080/TheExamAPI_war/api/tdepartments/tdepartments-by-course?courseUid=${
            this.selected[this.activeStep]["course"]
          }`
        )
        .then((response) => {
          this.suggestionsList[this.activeStep]["departmentList"] =
            response.data;

          this.$root.log(
            this.suggestionsList[this.activeStep]["departmentList"]
          );
        });
    },
    fetchClassesByDepartment() {
      // axios
      //   .get(
      //     `http://localhost:8080/TheExamAPI_war/api/tdepartmentclassmappings/tclasses-by-department?departmentUid=${
      //       this.selected[this.activeStep]["department"]
      //     }`
      //   )
      //   .then((response) => {
      //     // this.suggestionsList[this.activeStep]["classNameList"] =
      //     //   response.data;
      //     // this.$root.log(response.data[0]["uidDepartmentClassMapping"]);
      //     // this.departmentClassMappingList = response.data;
      //     response.data.forEach((item) => {
      //       this.departmentClassMappingList.push(item);
      //     });
      //     this.$root.log(this.departmentClassMappingList);
      //     response.data.forEach((element) => {
      //       // this.$root.log(response.data);
      //       this.suggestionsList[this.activeStep]["classNameList"].push(
      //         element["uidClassFk"]
      //       );
      //     });
      //     // this.$root.log(
      //     //   this.suggestionsList[this.activeStep]["classNameList"]
      //     // );
      //   });
      axios
        .get(
          `http://localhost:8080/TheExamAPI_war/api/tdepartmentclassmappings/tclasses-by-department?departmentUid=${
            this.selected[this.activeStep]["department"]
          }`
        )
        .then((response) => {
          this.suggestionsList[this.activeStep]["departmentClassMapping"] =
            response.data;

          response.data.forEach((item) => {
            this.departmentClassMappingList.push(item);
          });
          this.suggestionsList[this.activeStep]["classNameList"] =
            response.data.map((item) => {
              return item["uidClassFk"];
            });
        });
    },
    fetchSemesterByClass() {
      // this.selected[this.activeStep]["departmentClassMapping"]
      axios
        .get(
          `http://localhost:8080/TheExamAPI_war/api/tdepartmentclassmapsemestermappings/tsemesters-by-class?departmentClassMapUid=${
            this.selected[this.activeStep]["departmentClassMapping"]
          }`
        )
        .then((response) => {
          this.suggestionsList[this.activeStep][
            "departmentClassMappSemesterList"
          ] = response.data;

          response.data.forEach((item) => {
            this.departmentClassMappSemesterList.push(item);
          });
          this.suggestionsList[this.activeStep]["semesterList"] =
            response.data.map((item) => {
              return item["uidSemesterFk"];
            });
        });
    },
    fetchSubjectsBySemester() {
      axios
        .get(
          `http://localhost:8080/TheExamAPI_war/api/tsubjects/tsubjects-by-semester?semesterUid=${
            this.selected[this.activeStep]["departmentClassMappSemesterMapping"]
          }`
        )
        .then((response) => {
          this.suggestionsList[this.activeStep]["subjectList"] = response.data;

          response.data.forEach((item) => {
            this.subjectList.push(item);
          });
          this.$root.log(this.suggestionsList[this.activeStep]["subjectList"]);
          // this.suggestionsList[this.activeStep]["subjectList"] =
          //   response.data.map((item) => {
          //     return item;
          //   });
        });
    },
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
      axios
        .post("http://localhost:8080/TheExamAPI_war/api/texams", {
          stitle: `Exam ${this.dateOfExam}`,
          jtotalMarks: this.totalMarks,
          jpassingMarks: this.passingMarks,
          stypeOfExam: this.examType,
          dtdateOfExam: this.dateOfExam,
          jduration: this.examDuration,
          sstatusOfExam: this.examStatus,
          uidSubjectFk: {
            uidSubject: this.subject,
          },
        })
        .then((response) => {
          this.$root.log(response.data);
        });
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
  // min-height: 100vh;
  font-family: "Noto Sans", sans-serif;
  // background: radial-gradient(#DF5C2E, #A43227);
}
.form-container {
  @include flexbox();
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
    border-radius: 8px 8px 0 0;
    padding: 16px;
    background-color: $bg-primary-faded;
    // box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2), 0 10px 10px rgba(0, 0, 0, 0.2);
  }
  .progress {
    height: fit-content !important;
    background-color: transparent !important;
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
    border-radius: 0 0 8px 8px;
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
        height: 35px;
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
    .summary-field-name {
      text-transform: capitalize;
    }

    .actions {
      margin-top: 30px;
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
        font-size: 1rem;
        padding: 8px 24px;
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
.auto-complete {
  // height: 50px !important;
  // font-size: 1.2rem !important;
  border: none !important;
}
#inputStream .vs__dropdown-toggle {
  border: none;
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