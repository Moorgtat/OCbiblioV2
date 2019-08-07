<template>
  <div id="app">
    <div id="global-content">
    <div id="gauche"></div>
    <div id="centre">
      <div id="log">
        <router-link v-if="authenticated" to="/login" v-on:click.native="logout()"><md-button>Logout</md-button></router-link>
      </div>
    <router-view @authenticated="setAuthenticated" @SendUser="populateUser"/>
    </div>
    <div id="droite"></div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      authenticated: false,
      user: {
        id: '12568497',
        mail: 'userinconu@unknow.fr',
        pseudo: 'pa8za4553',
        password: 't7-6z45ty'
      }
    }
  },
  created () {
    if (this.authenticated === false) { this.$router.replace({ name: 'Login' }) }
  },
  methods: {
    populateUser (event) {
      this.user = event
    },
    setAuthenticated (status) {
      this.authenticated = status
    },
    logout () {
      this.authenticated = false
      this.user = ''
    }
  }
}
</script>

<style>
#app {
  font-family: Calibri Light, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  font-size: larger;
  color: #2c3e50;
}
#log {
  margin-left: 60%;
  margin-top: 35px;
}
#global-content{
  display: flex;
}
#centre{
  width: 70%;
  background-color: white;
}
#gauche{
  background-image: url("assets/Background/biblio2.jpg");
  opacity: 0.6;
  width: 15%;
}
#droite{
  background-image: url("assets/Background/biblio2.jpg");
  opacity: 0.6;
  width: 15%;
}
</style>
