<template>
  <div class="departments-main">
    <div class="departments-search-div">
      <!-- <auto-complete-input :dataList="['CS','IT','Botony','Mathematics','Physics']"
      v-model.trim="department"
      /> -->
      <input
        type="text"
        class="form-control"
        placeholder="Type here to search"
        v-model="searchQuery"
      />
    </div>
    <div class="departments-add-div">
      <button class="btn btn-primary">
        <router-link
          class="text-white text-decoration-none"
          :to="{ name: 'addDepartment' }"
          >Add Department</router-link
        >
      </button>
    </div>
    <div class="departments-list-div">
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
      <div class="departments-table">
        <div class="table-row header blue">
          <div class="cell">#</div>
          <div class="cell">Title of Department</div>
          <div class="cell">Details</div>
          <div class="cell"></div>
          <div class="cell"></div>
        </div>

        <div
          class="table-row"
          v-for="(department, index) in resultQuery"
          :key="department.uidDepartment"
        >
          <div class="cell" data-title="id">{{ index + 1 }}</div>
          <div class="cell" data-title="title">
            <a href="#">{{ department.stitle }}</a>
          </div>
          <div class="cell" data-title="details">
            {{ department.sdetails }}
          </div>
          <div class="cell">
            <router-link
              :to="{
                name: 'addDepartment',
                params: { id: department.uidDepartment },
              }"
            >
              <span>
                <box-icon name="edit-alt"></box-icon>
              </span>
            </router-link>
          </div>
          <div class="cell">
            <button @click="deleteDepartment($event, department.uidDepartment)">
              <span>
                <box-icon name="trash"></box-icon>
              </span>
            </button>
          </div>
        </div>

        <!-- <div class="table-row">
          <div class="cell" data-title="id">2</div>
          <div class="cell" data-title="title"><a href="#">IT</a></div>
          <div class="cell" data-title="details">
            Information Technology Department
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
          <div class="cell" data-title="title"><a href="#">Physics</a></div>
          <div class="cell" data-title="details">Physics Department</div>
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
  </div>
</template>

<script>
// import AutoCompleteInput from "../AutoCompleteInput/AutoCompleteInput.vue";
import axios from "axios";
export default {
  name: "DepartmentList",
  data: () => {
    return {
      department: "BSc",
      departments: [],
      searchQuery: null,
    };
  },
  components: {
    // AutoCompleteInput,
  },
  computed: {
    resultQuery() {
      if (this.searchQuery) {
        return this.departments.filter((item) => {
          return this.searchQuery
            .toLowerCase()
            .split(" ")
            .every((v) => item.stitle.toLowerCase().includes(v));
        });
      } else {
        return this.departments;
      }
    },
  },
  mounted() {
    this.$emit("pageTitle", "Departments");
    this.fetchDepartments();
  },
  methods: {
    fetchDepartments() {
      axios
        .get("http://localhost:8080/TheExamAPI_war/api/tdepartments")
        .then((response) => {
          this.departments = response.data;
        });
    },
    deleteDepartment(event, uid) {
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
            axios
              .delete(
                `http://localhost:8080/TheExamAPI_war/api/tdepartments/${uid}`
              )
              .then((response) => {
                console.log(response.data);
                this.fetchDepartments();
              });
            console.log("Delete API called");
          }
        },
      });
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
.departments-main {
  min-height: inherit;
  display: flex;
  flex-direction: column;
  max-width: 70%;
  margin: 0 auto;
  /* align-items: center; */
}
.departments-main h1 {
  text-align: start;
  font-size: 1.5rem;
  font-weight: bolder;
}
.departments-search-div {
  margin: 1rem 0;
  width: 100%;
}
.departments-add-div {
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
.departments-list-div {
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
  .departments-main {
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
  .departments-main {
    max-width: 90%;
  }
}

.departments-table {
  // margin: 0 0 40px 0;
  width: 100%;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.2);
  display: table;
}
@media screen and (max-width: 580px) {
  .departments-table {
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