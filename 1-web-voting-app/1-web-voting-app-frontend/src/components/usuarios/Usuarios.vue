<template>
<div class="usuarios">
  <router-link to="usuarios/novo" replace>Novo</router-link>
  <table class="tabela">
    <thead>
        <tr>
            <th>Nome</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="usuario in usuarios" :key="usuario.id">
            <td>{{usuario.nome}}</td>
            <td>{{usuario.email}}</td>
        </tr>
    </tbody>
  </table>
</div>
</template>

<script>
import {AXIOS} from './../http-common'
export default {
  name: 'Usuarios',
  data () {
    return {
      usuarios: []
    }
  },
  methods: {

  },
  mounted () {
    console.log('montado')
    AXIOS.get('api/usuarios')
      .then(response => {
        this.usuarios = response.data
      })
  }
}
</script>

<style>
</style>
