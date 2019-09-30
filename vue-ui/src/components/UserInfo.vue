<template>
    <div id="userinfo">
      <h2>Profil de {{ user.pseudo}}</h2>
      <p> Voici la liste des livres que tu empruntes en ce moment {{ user.pseudo}}. <br>
      Tu peux prolonger un emprunt une fois si tu n'as pas fini ta lecture. <br>
      N'oublie pas de rendre le livre que tu as emprunté en temps et en heure!</p>
      <md-button @click="listLoan(user.pseudo)">Mes emprunts</md-button>
      <md-button @click="affListAtt(user.pseudo)">Mes réservations</md-button>
      <md-button @click="listPastLoan(user.pseudo), listResAct(user.pseudo)">Afficher mes messages</md-button>
      <div id="tableResContainer" v-if="afficheur === 'mesreservations'">
        <md-table id="tableRes">
          <md-table-row>
            <md-table-head>Titre</md-table-head>
            <md-table-head>Delai d'attente estimé</md-table-head>
            <md-table-head>Annuler réservation</md-table-head>
            <md-table-head>Prendre réservation</md-table-head>
          </md-table-row>
          <md-table-row v-for="uReservation in ListuReservations" :key="uReservation.id">
            <md-table-cell>{{uReservation.nomLivre}} {{uReservation.id}}</md-table-cell>
            <md-table-cell>
              <md-button @click="calculer(uReservation.nomLivre, uReservation.id)">Calculer</md-button>
            </md-table-cell>
            <md-table-cell>
              <md-button @click="annulerReservation(uReservation.id, uReservation.nomLivre, user.mail)">Annuler</md-button>
            </md-table-cell>
            <md-table-cell>
              <md-button v-if="uReservation.actif === false" disabled>Prendre</md-button>
              <md-button v-else-if="uReservation.actif === true" @click="prendreReservation(uReservation.id, uReservation.nomLivre, user.pseudo)">Prendre</md-button>
            </md-table-cell>
          </md-table-row>
        </md-table>
      </div>
      <div id="tableLoanContainer" v-if="afficheur === 'mesemprunts'">
        <md-table id="tableLoan">
          <md-table-row>
            <md-table-head>Titre</md-table-head>
            <md-table-head>Fin du Prêt</md-table-head>
            <md-table-head>Prolonger emprunt</md-table-head>
            <md-table-head>Rendre emprunt</md-table-head>
          </md-table-row>
          <md-table-row v-for="loan in ListLoans" :key="loan.id">
            <md-table-cell>{{loan.nomLivre}}</md-table-cell>
            <md-table-cell>{{loan.finPret}}</md-table-cell>
            <md-table-cell>
              <!--Ticket #2 regles de gestion coté FRONT-->
              <md-button v-if="loan.authProlong === true" @click="prolongLoan(loan.id)">Prolonger</md-button>
              <md-button v-if="loan.authProlong === false" class="md-accent" disabled>Prolonger</md-button>
            </md-table-cell>
            <md-table-cell>
              <md-button @click="rendreHandle(loan.id, loan.nomLivre, user.mail)">Rendre</md-button>
            </md-table-cell>
          </md-table-row>
        </md-table>
      </div>
      <div v-if="afficheur === 'estimation'">
        <p>Vous êtes en position <strong>{{ this.placeFileAttente }}</strong> sur la liste d'attente. Votre réservation sera disponible
          au plus tôt dans <strong>{{ this.estimation }}</strong> jours.</p>
      </div>
      <div v-if="afficheur === 'prolongation'">
        <p> Vous avez prolonger votre prêt du livre <strong>{{loan.nomLivre}}</strong>
          jusqu'au <strong>{{loan.finPret}}</strong>.</p>
      </div>
      <div v-if="afficheur === 'rendu'">
        <p> Nous avons bien reçu votre livre. Merci <strong>{{user.pseudo}}</strong>.</p>
      </div>
      <div v-if="afficheur === 'mesmessages'">
        <div v-if="ListPastLoans.length === 0 && listResAct.length == 0"><p>Vous n'avez pas de message.</p></div>
        <p v-for="loan in ListPastLoans" :key="loan.id"> <font-awesome-icon icon="bell" /> &nbsp; Vous devez rendre
          <span id="pastnomlivre"> <b> {{loan.nomLivre}} </b> </span> depuis le
          <span id="pastfinpret"> <b> {{loan.finPret}} </b> </span>.
         </p>
        <p v-for="uReservation in listResActMess" :key="uReservation.id"> <font-awesome-icon icon="bell" /> Votre reservation
          de <b>{{uReservation.nomLivre}}</b> vous attends à la bibliothèque depuis le <b>{{ uReservation.dateActivation }}</b>.</p>
      </div>
      <div v-if="afficheur === 'reservationannule'">
        <p> <strong>{{user.pseudo}}</strong> nous te confirmons que ta réservation a bien été annulée.</p>
      </div>
      <div v-if="afficheur === 'reservationprise'">
        <p> <strong>{{user.pseudo}}</strong> tu as bien pris ta réservation.</p>
      </div>
    </div>
</template>

<script>import axios from 'axios'
export default {name: 'UserInfo',
  props: ['user'],
  data () {
    return {
      act: false,
      loan: {},
      estimation: null,
      placeFileAttente: null,
      afficheur: ''
    }
  },
  methods: {
    /* eslint-disable */
    listLoan (pseudo) {
        this.afficheur = "chargement"
      axios.get('http://localhost:8282/loan-service/ListLoans/?pseudo=' + pseudo)
        .then(response => {
          this.ListLoans = response.data
          console.log('succes', response)
          this.afficheur = "mesemprunts"
        }, (response) => {
          console.log('erreur', response)
        })
    },
    listPastLoan (pseudo) {
        this.afficheur = "chargement"
      axios.get('http://localhost:8282/loan-service/ListPastLoans/?pseudo=' + pseudo)
        .then(response => {
          this.ListPastLoans = response.data
          this.afficheur = "mesmessages"
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    },
    listResAct (nomUtil){
      axios.get('http://localhost:8282/reservation-service/FileResActMessage/?nomUtil=' + nomUtil)
          .then(response => {
              this.listResActMess = response.data
              this.afficheur = "mesmessages"
              console.log('succes', response)
          }, (response) => {
              console.log('error', response)
          })
    },
    prolongLoan (patchId) {
        this.afficheur = "chargement"
      axios.post('http://localhost:8282/loan-service/PatchLoan/?id=' + patchId)
        .then(response => {
          this.loan = response.data
          this.afficheur = "prolongation"
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    },
    affListAtt(pseudo){
        this.afficheur = "chargement"
        axios.get('http://localhost:8282/reservation-service/FileAttente/?nomUtil=' + pseudo)
            .then(response => {
                this.ListuReservations = response.data
                this.afficheur = "mesreservations"
                console.log('succes', response)
            }, (response) => {
                console.log('erreur', response)
            })
    },
    annulerReservation(idRes, nomLivre, userMail){
        this.afficheur = "chargement"
        axios.get('http://localhost:8282/reservation-service/listeUresByNLAndNA/?nomLivre=' + nomLivre)
            .then(response => {
                console.log('succes', response)
                this.listresByNLandNA = response.data
             axios.get('http://localhost:8282/reservation-service/isResActiv/?id=' + idRes)
                 .then(response => {
                   console.log('succes2', response)
                     this.act = response.data
                     //conditions
                     if(this.listresByNLandNA.length === 0 && this.act === false) {
                         axios.get("http://localhost:8282/reservation-service/DeleteRes/?id=" + idRes)
                             .then(response =>{
                                 this.afficheur = "reservationannule"
                                 console.log('succes2', response)
                             })
                     }
                     if(this.listresByNLandNA.length === 0 && this.act === true ){
                         axios.get("http://localhost:8282/reservation-service/DeleteRes/?id=" + idRes)
                             .then(response =>{
                                 console.log('succes3', response)
                                 //bookplusone
                                 axios.patch('http://localhost:8282/book-service/bookPlusOne/?titre=' + nomLivre)
                                     .then(response => {
                                         this.afficheur = "reservationannule"
                                         console.log('succes3', response)
                                     })
                             })
                     }
                     if(this.listresByNLandNA.length > 0 && this.act === false) {
                         axios.get("http://localhost:8282/reservation-service/DeleteRes/?id=" + idRes)
                             .then(response =>{
                                 this.afficheur = "reservationannule"
                                 console.log('succes2', response)
                             })
                     }
                     if(this.listresByNLandNA.length > 0 && this.act === true ){
                         axios.get("http://localhost:8282/reservation-service/DeleteRes/?id=" + idRes)
                             .then(response =>{
                                 console.log('succes3', response)
                                 // getold true: nothing false: bookplusone
                                 axios.patch("http://localhost:8282/reservation-service/getOldUres/?nomLivre=" + nomLivre + "&userMail=" + userMail)
                                     .then(response => {
                                         this.afficheur = "reservationannule"
                                         console.log('succes2', response)
                                     })
                             })
                     }
                 })
            })
    },
    rendreHandle(idLoan, nomLivre, userMail){
        this.afficheur = "chargement"
        axios.get('http://localhost:8282/reservation-service/listResNomLivre/?nomLivre=' + nomLivre)
            .then(response => {
                console.log('succes', response)
                this.listUresByNomLivre = response.data
                  if(this.listUresByNomLivre.length === 0){
                     axios.get('http://localhost:8282/loan-service//DeleteLoan/?id=' + idLoan)
                         .then(response => {
                             console.log('succes2', response)
                             axios.patch('http://localhost:8282/book-service/bookPlusOne/?titre=' + nomLivre)
                                 .then(response => {
                                     console.log('succes3', response)
                                     this.afficheur = "rendu"
                                 })
                         })
                  }
                  if(this.listUresByNomLivre.length > 0){
                     axios.patch('http://localhost:8282/reservation-service/getOldUres/?nomLivre=' + nomLivre + '&userMail=' + userMail)
                         .then(response =>{
                             console.log('succes4', response)
                             axios.get('http://localhost:8282/loan-service//DeleteLoan/?id=' + idLoan)
                                 .then(response => {
                                     console.log('succes5', response)
                                     this.afficheur = 'rendu'
                                 })
                         })
                  }
            })
    },
    prendreReservation(idRes, nomLivre, nomUtil){
        this.afficheur = "chargement"
        axios.get("http://localhost:8282/reservation-service/DeleteRes/?id=" + idRes)
            .then(response => {
                console.log('succes', response)
                axios.post('http://localhost:8282/loan-service/createLoan/?pseudoEmprunteur=' + nomUtil + '&nomLivre=' + nomLivre)
                    .then(response => {
                        this.afficheur = "reservationprise"
                        console.log('succes', response)
                    }, (response) => {
                        console.log('erreur', response)
                    })
            }, (response) => {
                console.log('erreur', response)
            })
    },
      calculer(nomLivre, id){
          this.afficheur = "chargement"
          axios.get('http://localhost:8282/reservation-service/getListOlderRes/?nomLivre=' + nomLivre + '&id=' + id)
              .then(response => {
                  console.log('succes', response)
                  this.listOlderRes = response.data
                   axios.get('http://localhost:8282/loan-service/listLoanByNomLivre/?NomLivre=' + nomLivre)
                       .then(response =>{
                           console.log('succes2', response)
                           this.listLoansbyNomLivre = response.data
                           this.placeFileAttente = this.listOlderRes.length + 1
                           this.estimation = this.listOlderRes.length * 3 + this.listLoansbyNomLivre.length * 3
                           this.afficheur = "estimation"
                       })
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
  align-content: center;
}
#tableRes{
  align-content: center;
}
#pastfinpret{
 color: darkred;
  }
#pastnomlivre{
  color: darkred;
}
</style>
