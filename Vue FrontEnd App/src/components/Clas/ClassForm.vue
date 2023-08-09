<template>
  <div class="class-add-container">
    <div class="header-div">
      <span>
        <router-link :to="{ name: 'class' }">
          <i class="fa fa-arrow-left" aria-hidden="true"></i>
        </router-link>
      </span>
      <h1 v-if="$route.params.id == null">Add Class</h1>
      <h1 v-if="$route.params.id != null">Update Class</h1>
    </div>
    <!-- <form class="form-main">
      <div class="input-div">
        <div class="input-container">
          <label class="input-label"
            >Enter title of stream<span class="text-red-600">*</span></label
          >
          <div class="input-field-container">
            <input
              type="text"
              v-model.trim="$v.title.$model"
              class="form-control"
              :class="{
                'is-invalid': $v.title.$error,
                'is-valid': !$v.title.$invalid,
              }"
              name="title"
              placeholder="Science, Commerce, Arts, Law"
            />
            <div class="valid-feedback">Title is valid</div>
            <div class="invalid-feedback">
              <p v-if="!$v.title.required">Title is required</p>
              <p v-if="!$v.title.minLength">
                Title must have at least
                {{ $v.title.$params.minLength.min }} letters
              </p>
              <p v-if="!$v.title.maxLength">
                Title must have at most
                {{ $v.title.$params.maxLength.max }} letters
              </p>
            </div>
            <div class="error-div" v-if="formErrors.title.length">
              <p
                class="text-red-600"
                v-for="(err, idx) in formErrors.title"
                :key="idx"
              >
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
                <span class="text-red-600 p-1">{{ err.msg }}</span>
              </p>
            </div>
          </div>
        </div>
        <div class="input-container">
          <label class="input-label"
            >Enter details of stream<span class="text-red-600">*</span></label
          >
          <div class="input-field-container">
            <textarea
              v-model="details"
              name="details"
              rows="10"
              placeholder="Main stream of law faculties"
            />
            <div class="error-div" v-if="formErrors.details.length">
              <p
                class="text-red-600"
                v-for="(err, idx) in formErrors.details"
                :key="idx"
              >
                <i class="fa fa-exclamation-circle" aria-hidden="true"></i>
                <span class="text-red-600 p-1">{{ err.msg }}</span>
              </p>
            </div>
          </div>
        </div>
        <button type="submit" v-if="$route.params.id == null">
          Create Course
        </button>
        <button v-if="$route.params.id != null">Save Course</button>
      </div>
    </form> -->
    <form class="row g-3 needs-validation mt-5">
      <div class="row">
        <label for="inputCourse" class="form-label">Select department</label>
        <v-select
          id="inputCourse"
          class="form-control col-md-8 auto-complete"
          v-model.trim="$v.department.$model"
          :class="{
            'is-invalid': $v.department.$error,
            'is-valid': !$v.department.$invalid,
          }"
          :options="['CS','IT','Physics','Botony']"
        />
        <div class="invalid-feedback alert alert-danger">
          <p v-if="!$v.department.required">Department is required</p>
        </div>
      </div>
      <div class="row">
        <label for="inputTitle" class="form-label">Title of Class</label>
        <input
          type="text"
          class="form-control col-md-8"
          v-model.trim="$v.title.$model"
          :class="{
            'is-invalid': $v.title.$error,
            'is-valid': !$v.title.$invalid,
          }"
          id="inputTitle"
          placeholder="Science, Commerce, Arts, ..."
        />
        <div class="invalid-feedback alert alert-danger">
          <p v-if="!$v.title.required">Title is required</p>
          <p v-if="!$v.title.minLength">
            Title must have at least
            {{ $v.title.$params.minLength.min }} letters
          </p>
          <p v-if="!$v.title.maxLength">
            Title must have at most {{ $v.title.$params.maxLength.max }} letters
          </p>
        </div>
      </div>
      <div class="row">
        <label for="inputDetails" class="form-label">Details</label>
        <textarea
          class="form-control col-md-8"
          v-model.trim="$v.details.$model"
          :class="{
            'is-invalid': $v.details.$error,
            'is-valid': !$v.details.$invalid,
          }"
          id="inputDetails"
          placeholder="Main department of all commerce facutlies"
        />
        <div class="invalid-feedback alert alert-danger">
          <p v-if="!$v.details.required">Details is required</p>
          <p v-if="!$v.details.minLength">
            Details must have at least
            {{ $v.details.$params.minLength.min }} letters
          </p>
          <p v-if="!$v.details.maxLength">
            Details must have at most
            {{ $v.details.$params.maxLength.max }} letters
          </p>
        </div>
      </div>
      <div class="col">
        <!-- <button class="btn btn-primary" type="submit">Submit form</button> -->
        <button
          class="btn btn-primary"
          type="submit"
          id="btn-create"
          v-if="$route.params.id == null"
          @click="submitForm"
          :disabled="submitStatus === 'PENDING'"
        >
          Create Class
        </button>
        <button
          class="btn btn-primary"
          type="submit"
          id="btn-update"
          v-if="$route.params.id != null"
          @click="submitForm"
          :disabled="submitStatus === 'PENDING'"
        >
          Update Class
        </button>
      </div>
    </form>
    <!-- <form>
      <div class="form-group row">
        <label for="inputTitle" class="col-sm-4 col-form-label">Title</label>
        <div class="col-sm-8 justify-content-between">
          <input
            type="text"
            class="form-control"
            id="inputTitle"
            placeholder="Science, Commerce, Arts, Law"
            v-model.trim="$v.title.$model"
            :class="{
              'is-invalid': $v.title.$error,
              'is-valid': !$v.title.$invalid,
            }"
          />
        </div>
      </div>
      <div>
        <div class="col-sm-4  valid-feedback alert alert-success">Title is valid</div>
        <div class="col-sm-8 invalid-feedback alert alert-danger">
          <p v-if="!$v.title.required">Title is required</p>
          <p v-if="!$v.title.minLength">
            Title must have at least
            {{ $v.title.$params.minLength.min }} letters
          </p>
          <p v-if="!$v.title.maxLength">
            Title must have at most {{ $v.title.$params.maxLength.max }} letters
          </p>
        </div>
      </div>
    </form> -->
    <!-- <form>
      <div class="form-group row">
        <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
          <input
            type="text"
            readonly
            class="form-control-plaintext"
            id="staticEmail"
            value="email@example.com"
          />
        </div>
      </div>
      <div class="form-group row">
        <label for="inputPassword" class="col-sm-2 col-form-label"
          >Password</label
        >
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputPassword"
            placeholder="title"
            v-model.trim="$v.title.$model"
            :class="{
              'is-invalid': $v.title.$error,
              'is-valid': !$v.title.$invalid,
            }"
          />
        </div>
      </div>
      <div class="form-group row">
        <label for="inputPas" class="col-sm-2 col-form-label"></label>
        <div class="col-sm-10">
          <div class="col-sm-8 invalid-feedback alert alert-danger">
            <p v-if="!$v.title.required">Title is required</p>
            <p v-if="!$v.title.minLength">
              Title must have at least
              {{ $v.title.$params.minLength.min }} letters
            </p>
            <p v-if="!$v.title.maxLength">
              Title must have at most
              {{ $v.title.$params.maxLength.max }} letters
            </p>
          </div>
        </div>
      </div>
    </form> -->
  </div>
</template>

<script>
import { TYPE } from "vue-toastification";
import { required, minLength, maxLength } from "vuelidate/lib/validators";
import vSelect from "vue-select";
import "vue-select/dist/vue-select.css";
export default {
  name: "ClassAdd",
  components: {
    vSelect,
  },
  data: () => {
    return {
      title: "",
      details: "",
      department: "Physics",
      submitStatus: null,
      errMsg: [],
      formErrors: {
        title: [],
        details: [],
      },
    };
  },
  validations: {
    title: {
      required,
      minLength: minLength(2),
      maxLength: maxLength(50),
    },
    details: {
      required,
      minLength: minLength(8),
      maxLength: maxLength(100),
    },
    department: {
      required,
    },
  },
  // watch: {
  //   title(value) {
  //     this.title = value;
  //     this.validateTitle(value);
  //   },
  //   details(value) {
  //     this.details = value;
  //     this.validateDetails(value);
  //   },
  // },
  methods: {
    validateTitle(value) {
      let msg = "";
      let id = 0;
      if (value.length == 0) {
        msg = "Title cannot be empty";
        id = 1;
        // this.errMsg["title"] = msg;
        const found = this.formErrors["title"].some(function (value) {
          return value.id === id;
        });
        if (!found && msg) this.formErrors["title"].push({ id, msg });
      }
      if (value.length < 3) {
        let difference = 3 - value.length;
        msg =
          "Title Must be of minimum 3 characters! " +
          difference +
          " characters left";
        id = 2;
        // this.errMsg["title"] = msg;
        const found = this.formErrors["title"].some(function (value) {
          return value.id === id;
        });
        if (!found && msg) this.formErrors["title"].push({ id, msg });
        // this.errMsg["title"] = msg;
        // this.formErrors["title"].push(msg);
      } else {
        // this.errMsg["title"] = "";
        this.formErrors["title"] = [];
      }
    },
    validateDetails(value) {
      let msg = "";
      let id = 0;
      if (value.length == 0) {
        id = 1;
        msg = "Details cannot be empty";
        const found = this.formErrors["details"].some(function (value) {
          return value.id === id;
        });
        if (!found && msg) this.formErrors["details"].push({ id, msg });
      }
      if (value.length < 8) {
        let difference = 8 - value.length;
        // this.errMsg["details"] = msg;
        msg =
          "Details Must be of minimum 8 characters! " +
          difference +
          " characters left";
        id = 2;
        const found = this.formErrors["details"].some(function (value) {
          return value.id === id;
        });
        if (!found && msg) this.formErrors["details"].push({ id, msg });
      } else {
        // this.errMsg["details"] = "";
        this.formErrors["details"] = [];
      }
    },
    resetErrors() {
      this.formErrors = {
        title: [],
        details: [],
      };
    },
    createClass() {
      this.resetErrors();
      this.validateTitle(this.title);
      this.validateDetails(this.details);
      // this.$root.log(this.formErrors);
      const isTitleEmpty = this.title.length == 0;
      const isDetailsEmpty = this.details.length == 0;
      // if (isTitleEmpty) {
      //   this.errMsg["title"] = "Title cannot be empty";
      // }
      // if (isDetailsEmpty) {
      //   this.errMsg["details"] = "Details cannot be empty";
      // }
      if (
        !isTitleEmpty &&
        !isDetailsEmpty &&
        this.formErrors["title"].length == 0 &&
        this.formErrors["details"].length == 0
      ) {
        this.$root.log("Create API called");
      }
    },
    updateClass() {
      const isTitleEmpty = this.title.length == 0;
      const isDetailsEmpty = this.details.length == 0;
      if (
        !isTitleEmpty &&
        !isDetailsEmpty &&
        this.formErrors["title"].length == 0 &&
        this.formErrors["details"].length == 0
      ) {
        this.$root.log("Update API called");
      }
    },
    submitForm(event) {
      event.preventDefault();
      const btnElement = event.target;
      if (btnElement.id == "btn-create") {
        this.$v.$touch();
        if (this.$v.$invalid) {
          this.submitStatus = "ERROR";
          this.$toast("Form has errors.Please fill in correct data", {
            type: TYPE.ERROR,
            timeout: 4000,
          });
        } else {
          // do your submit logic here
          this.submitStatus = "PENDING";
          setTimeout(() => {
            this.submitStatus = "OK";
            this.$toast("Class created successfully", {
              type: TYPE.SUCCESS,
              timeout: 2000,
            });
          }, 500);
        }
      } else if (btnElement.id == "btn-update") {
        this.$v.$touch();
        if (this.$v.$invalid) {
          this.submitStatus = "ERROR";
        } else {
          // do your submit logic here
          this.submitStatus = "PENDING";
          setTimeout(() => {
            this.submitStatus = "OK";
            this.$toast("Class updated successfully", {
              type: TYPE.SUCCESS,
              timeout: 2000,
            });
          }, 500);
        }
      }
    },
  },
  mounted() {
    if (this.$route.params.id != null) {
      const className = {
        title: "FY",
        details: "First Year CS class",
      };
      this.title = className.title;
      this.details = className.details;
    }
  },
};
</script>

<style lang="scss" scoped>
$bg-primary: #2980b9;
$bg-primary-faded: #dfe7f3;
$bg-primary-hover: #02409ee3;
$bg-primary-header: #297fb9d3;

.row {
  margin-left: 0 !important;
}
.row + .row {
  margin-top: 1rem !important;
}
.row > * {
  padding-left: 0 !important;
  padding-right: 0 !important;
}
.row input {
  padding: 0.5rem 1rem !important;
}
.row textarea {
  resize: none;
  padding: 0.5rem 1rem !important;
}
.row .alert {
  padding: 0.5rem !important;
}

.header-div {
  display: flex;
  // align-items: center;
  h1 {
    margin-left: 2rem;
    font-size: 1.5rem;
    font-weight: bold;
  }
  .fa {
    font-size: 1.2rem;
    padding: 4px 8px 4px 0;
    border-radius: 4px;
  }
}
.class-add-container {
  min-height: inherit;
  display: flex;
  flex-direction: column;
  max-width: 75%;
  margin: 0 auto;
}
.form-main {
  //   margin-top: 100px;
  //   width: 50%;
  //   margin: 0 auto;
  min-height: 400px;
  display: flex;
  // justify-content: center;
  align-items: center;
}
.auto-complete {
  border: none;
}
.input-div {
  margin: 16px 0 16px 0;
  // margin-top: 100px;
  width: 100%;
  //   margin: 0 auto;
  button {
    float: right;
    margin-top: 3rem;
    text-align: center;
    padding: 8px 48px;
    text-decoration: none;
    font-size: 1rem;
    transition: all 0.3s;
    border-radius: 4px;
    background-color: $bg-primary;
    color: whitesmoke;
    // font-size: 1.2rem;
    font-weight: bold;
  }
  button:hover {
    background-color: $bg-primary-hover;
    color: #dfe7f3;
  }
}
.input-container {
  display: flex;
  justify-content: space-between;
  // flex-direction: column;
  margin-top: 16px;
}
.input-container label {
  flex: 0 0 40%;
}
.input-container input {
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
  height: 100px;
  font-family: "Noto Sans", sans-serif;
  font-size: 1.2rem;
  outline: none;
  background: #f1f1f1;
  box-shadow: none;
  border: 1px solid rgba(66, 65, 65, 0.219);
  border-radius: 4px;
  padding: 4px 8px;
  resize: none;
}
.input-container .input-field-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: #f1f1f1;
}
.input-container .input-field-select {
  width: 100%;
}
.error-div {
  background-color: white;
  padding: 4px 0;
}

p {
  margin: 0 !important;
}
.alert {
  margin: 0.5rem 0;
  padding: 0.3rem 0.5rem;
}
@media screen and (max-width: 768px) {
  .class-add-container {
    max-width: 80%;
    // margin-left: 100px;
  }
  .input-div .input-container {
    flex-direction: column;
  }
}
</style>