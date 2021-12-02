import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/event',
    name: 'Event',
    component: () => import('../views/Event.vue')
  },
  {
    path: '/people',
    name: 'People',
    component: () => import('../views/People.vue')
  },
  {
    path: '/video',
    name: 'Videos',
    component: () => import('../views/Videos.vue')
  },
  {
    path: '/video/:id',
    name: 'Video',
    component: () => import('../components/Video.vue')
  },
  {
    path: '/music',
    name: 'Musics',
    component: () => import('../views/Musics.vue')
  },
  {
    path: '/music/:id',
    name: 'Music',
    component: () => import('../components/Music.vue')
  },
  {
    path: '/comment',
    name: 'Comment',
    component: () => import('../views/Comment.vue')
  },
  {
    path: '/user',
    name: 'User',
    component: () => import('../views/User.vue')
  },
  {
    path: '/user/edit',
    name: 'Edit',
    component: () => import('../components/Edit.vue')
  },
  {
    path: '/user/avatar',
    name: 'Avatar',
    component: () => import('../components/Avatar.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
