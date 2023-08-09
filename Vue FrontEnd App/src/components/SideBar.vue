<template>
  <div id="app">
    <!--Sidebar with Dimmer -->
    <div class="fixed flex z-40">
      <!-- Sidebar -->
      <div
        class="absolute flex top-0 h-screen z-20"
        :class="[right ? 'right-0 flex-row' : 'left-0 flex-row-reverse']"
      >
        <!--Drawer -->
        <button
          @click.prevent="toggle()"
          class="
            w-12
            h-12
            p-1
            m-2
            rounded
            text-green
            bg-white
            text-center
            focus:outline-none
            transition-color
            duration-300
            toggler
          "
        >
          <span class="">
            <svg viewBox="0 0 24 24" class="mx-auto w-6 h-6 fill-current">
              <path
                fill-rule="evenodd"
                d="M4 5h16a1 1 0 0 1 0 2H4a1 1 0 1 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2zm0 6h16a1 1 0 0 1 0 2H4a1 1 0 0 1 0-2z"
              ></path>
            </svg>
          </span>
        </button>

        <!-- Sidebar Content -->
        <div
          ref="content"
          class="
            transition-all
            duration-700
            overflow-hidden
            flex
            items-center
            justify-center
            sidebar
          "
          :class="[open ? 'max-w-lg' : 'max-w-0']"
        >
          <!-- <div class="w-48 text-center font-bold text-xl">Sidebar</div>
          <slot></slot> -->
          <ul class="mx-4 w-48 text-center text-xl link-list">
            <!-- <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
                active-link
              "
            >
              Home
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              Add Question
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              Create Exam
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              Configure Exam
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              Profile
            </li> -->
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
                active-link
              "
            >
              <router-link to="/">Home</router-link>
            </li>
            
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              <router-link to="/streams">Streams</router-link>
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              <router-link to="/courses">Courses</router-link>
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              <router-link to="/departments">Departments</router-link>
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              <router-link to="/classes">Classes</router-link>
            </li>
            <li
              class="
                mt-4
                py-2
                px-2
                hover:text-white
                rounded
                transition-all
                duration-300
                cursor-pointer
              "
            >
              <router-link to="/subjects">Subjects</router-link>
            </li>
          </ul>
        </div>
      </div>

      <transition name="fade">
        <!-- Dimmer -->
        <div
          v-if="dimmer && open"
          @click="toggle()"
          class="flex-1 bg-gray-400 bg-opacity-75 active:outline-none z-10"
        />
      </transition>
    </div>

    <!-- Page Content -->
    <div
      class="
        absolute
        rounded
        bg
        w-full
        h-full
        flex flex-col
        justify-center
        items-center
      "
    >
      <router-view />
      <!-- <stepper-form /> -->
    </div>
  </div>
</template>

<script>
// import StepperForm from "./StepperForm.vue";
export default {
  name: "SideBar",
  components: {
    // StepperForm,
  },
  data() {
    return {
      open: false,
      dimmer: true,
      right: false,
    };
  },
  methods: {
    toggle() {
      this.open = !this.open;
    },
  },
};
</script>

<style lang="scss" scoped>
/* html {
	background: #efefef;
} */

$bg-primary: #0d6efd;
$bg-primary-faded: #dfe7f3;
$bg-success: #198754;
$bg-danger: #dc3545;

.sidebar {
  background-color: $bg-primary-faded;
}
.toggler:hover {
  background-color: $bg-primary;
  color: #dfe7f3;
}
.link-list {
  li:hover {
    background-color: $bg-primary;
    color: #dfe7f3;
  }
  .active-link {
    background-color: $bg-primary;
    color: #dfe7f3;
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 1s ease-out;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
