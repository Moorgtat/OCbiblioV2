<template>
    <div id="userinfo">
      <h2>Profil de {{ user.pseudo}}</h2>
      <p> Voici la liste des livres que tu empruntes en ce moment {{ user.pseudo}}. <br>
      Tu peux prolonger un emprunt une fois si tu n'as pas fini ta lecture. <br>
      N'oublie pas de rendre le livre que tu as emprunté en temps et en heure!</p>
      <md-button @click="listLoan(user.pseudo)">Afficher mes emprunts en cours</md-button>
      <md-button @click="listPastLoan(user.pseudo)">Afficher mes messages</md-button>
      <div id="tableLoanContainer" v-if="clicked">
        <md-table id="tableLoan">
          <md-table-row>
            <md-table-head>Titre</md-table-head>
            <md-table-head>Fin du Prêt</md-table-head>
            <md-table-head>Prolonger</md-table-head>
            <md-table-head>Rendre</md-table-head>
          </md-table-row>
          <md-table-row v-for="loan in ListLoans" :key="loan.id">
            <md-table-cell>{{loan.nomLivre}}</md-table-cell>
            <md-table-cell>{{loan.finPret}}</md-table-cell>
            <md-table-cell>
              <md-button v-if="loan.authProlong == true" @click="PatchLoan(loan.id)">Prolonger</md-button>
              <md-button v-if="loan.authProlong == false" class="md-accent" disabled>Prolonger</md-button>
            </md-table-cell>
            <md-table-cell><md-button @click="DeleteLoan(loan.id)">Rendre</md-button></md-table-cell>
          </md-table-row>
        </md-table>
      </div>
      <div v-if="prolonger">
        <p> Vous avez prolonger votre prêt du livre <strong>{{loan.nomLivre}}</strong>
          jusqu'au <strong>{{loan.finPret}}</strong>.</p>
      </div>
      <div v-if="rendre">
        <p> Nous avons bien reçu votre livre. Merci <strong>{{user.pseudo}}</strong>.</p>
      </div>
      <div v-if="past">
        <div v-if="ListPastLoans.length == 0"><p>Vous n'avez pas de message.</p></div>
        <p v-for="loan in ListPastLoans" :key="loan.id"> <font-awesome-icon icon="bell" /> &nbsp; Vous devez rendre
          <span id="pastnomlivre"> <b> {{loan.nomLivre}} </b> </span> depuis le
          <span id="pastfinpret"> <b> {{loan.finPret}} </b> </span>.
         </p>
      </div>
    </div>
</template>

<script>import axios from 'axios'
export default {name: 'UserInfo',
  props: ['user'],
  data () {
    return {
      loan: {},
      rendre: false,
      prolonger: false,
      clicked: false,
      past: false
    }
  },
  methods: {
    /* eslint-disable no-console */
    listLoan (pseudo) {
      this.rendre = false
      this.prolonger = false
      this.clicked = false
      this.past = false
      axios.get('http://localhost:8282/loan-service/ListLoans/?pseudo=' + pseudo)
        .then(response => {
          this.ListLoans = response.data
          console.log('succes', response)
          this.clicked = true
        }, (response) => {
          console.log('erreur', response)
        })
    },
    listPastLoan (pseudo) {
      this.past = false
      this.rendre = false
      this.prolonger = false
      this.clicked = false
      axios.get('http://localhost:8282/loan-service/ListPastLoans/?pseudo=' + pseudo)
        .then(response => {
          this.ListPastLoans = response.data
          console.log('succes', response)
          this.past = true
        }, (response) => {
          console.log('erreur', response)
        })
    },
    PatchLoan (patchId) {
      this.clicked = false
      this.prolonger = true
      axios.post('http://localhost:8282/loan-service/PatchLoan/?id=' + patchId)
        .then(response => {
          this.loan = response.data
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    },
    DeleteLoan (deleteId) {
      this.clicked = false
      this.rendre = true
      axios.get('http://localhost:8282/loan-service/DeleteLoan/?id=' + deleteId)
        .then(response => {
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    }
  }
}

</script>

<style scoped>
#userinfo{
  margin: 20px;
}
#tableLoanContainer{
  margin-left: 10%;
}
#tableLoan{
  width: 90%;
}
#pastfinpret{
 color: darkred;
  }
#pastnomlivre{
  color: darkred;
}
</style>
