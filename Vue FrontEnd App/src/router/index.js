import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/streams',
    name: 'stream',
    component: () => import('../components/Stream/StreamList.vue')
  },
  {
    path: '/courses',
    name: 'course',
    component: () => import('../components/Course/CourseList.vue')
  },
  {
    path: '/departments',
    name: 'department',
    component: () => import('../components/Department/DepartmentList.vue')
  },
  {
    path: '/classes',
    name: 'class',
    component: () => import('../components/Clas/ClassList.vue')
  },
  {
    path: '/subjects',
    name: 'subject',
    component: () => import('../components/Subject/SubjectList.vue')
  },
  {
    path: '/streams/add',
    name: 'addStream',
    component: () => import('../components/Stream/StreamForm.vue')
  },
  {
    path: '/courses/add',
    name: 'addCourse',
    component: () => import('../components/Course/CourseForm.vue')
  },
  {
    path: '/departments/add',
    name: 'addDepartment',
    component: () => import('../components/Department/DepartmentForm.vue')
  },
  {
    path: '/classes/add',
    name: 'addClass',
    component: () => import('../components/Clas/ClassForm.vue')
  },
  {
    path: '/subjects/add',
    name: 'addSubject',
    component: () => import('../components/Subject/SubjectForm.vue')
  },
  {
    path: '/questions/add',
    name: 'addQuestionToBank',
    component: () => import('../components/Question/QuestionForm.vue')
  },
  {
    path: '/create-exam',
    name: 'createExam',
    component: () => import('../components/ExamCreation/ExamCreationForm.vue')
  },
  {
    path: '/exam-configure',
    name: 'examConfigure',
    component: () => import('../components/ExamConfiguration/ExamConfiguration.vue')
  },
  {
    path: '/add-custom-question',
    name: 'addCustomQuestion',
    component: () => import('../components/Question/QuestionFormCustom.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
