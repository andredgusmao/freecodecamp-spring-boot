<template>
<div class="novousuario">
  Nome: <input type="text" v-model="usuario.nome"/>
  Email: <input type="text" v-model="usuario.email"/>
  Senha: <input type="password" v-model="usuario.senha"/>

  <button @click="salvar()">Salvar</button>
</div>
</template>

<script>
import {AXIOS} from './../http-common'
export default {
  name: 'NovoUsuario',
  data () {
    return {
      usuario: { nome: '', email: '', senha: '' }
    }
  },
  methods: {
    salvar () {
      AXIOS.post('api/usuarios', this.usuario)
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
