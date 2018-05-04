import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import NovoUsuario from '@/components/usuarios/NovoUsuario'
import Usuarios from '@/components/usuarios/Usuarios'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/usuarios/novo',
      name: 'NovoUsuario',
      component: NovoUsuario
    },
    {
      path: '/usuarios',
      name: 'Usuarios',
      component: Usuarios
    }
  ]
})
