<template>
<div class="login">
  Email: <input type="text" v-model="usuario.email"/>
  Senha: <input type="password" v-model="usuario.senha"/>

  <button @click="login()">Login</button>
</div>
</template>

<script>
import {AXIOS} from './http-common'
export default {
  name: 'Login',
  data () {
    return {
      usuario: { email: '', senha: '' }
    }
  },
  methods: {
    login () {
      AXIOS.post('api/usuarios/login', this.usuario)
        .then(response => {
          console.log(response.status)
          this.$router.push('/usuarios')
        })
        .catch(e => {
          console.log(e)
        })
    }
  }
}
</script>

<style>
</style>
