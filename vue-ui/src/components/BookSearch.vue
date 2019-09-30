<template>
  <div id='BookSearch'>
    <h2>Je cherche un livre</h2>
     <p>Tous nos livres sont disponible ici {{user.pseudo}}! Clique sur chercher<br>
       pour tous les afficher ou lance une recherche par Titre et par Auteur. <br>
       Tu peux emprunter ou réserver un maximum de 3 livres.</p>
    <div class="md-content" id="form-content">
      <form id="form-search-books" class="form-group label-floating">
        <div class="md-input-container">
          <md-field>
            <label>Titre</label>
            <md-input id="titre" name="titre" type="text" v-model="titre"></md-input>
          </md-field>
          <md-field>
            <label>Auteur</label>
            <md-input id="auteur" name="auteur" type="text" v-model="auteur"></md-input>
          </md-field>
        </div>
        <md-button class="md-raised md-default" @click="searchbooks(), countLoans(user.pseudo), countuRes(user.pseudo)">Chercher</md-button>
      </form>
    </div>
    <div id="global-container">
      <div v-if="afficheur === 'afficherLivres'" class="md-content" id="booksContainer">
        <md-card id="card-expanse" v-for="book in ListBooks" :key="book.id">
          <md-card-media>
            <img :src='book.image' alt="couverture du livre">
          </md-card-media>
          <md-card-header>
            <div class="md-subhead">Quantité restante :{{book.quantite}}</div>
            <div class="md-title">{{book.titre}}</div>
            <div class="md-subhead">{{book.auteur}}</div>
          </md-card-header>
          <md-card-expand>
            <md-card-actions md-alignment="space-between">
              <div>
                <md-button v-if="intcountloans <= 2 && book.quantite >= 1" href="#find" @click="gestionEmprunt(book.titre, user.pseudo, book.quantite)">
                  Emprunter
                </md-button>
                <md-button v-if="intcountloans === 3 && book.quantite >= 1" class="md-accent" disabled>Emprunter</md-button>
              </div>
              <div  v-if="book.quantite === 0">
                <md-button v-if="intcountuRes <= 2" href="#find" @click="gestionReservation(book.titre, user.pseudo, book.quantiteMax)">Réserver</md-button>
                <md-button v-if="intcountuRes === 3" class="md-accent" disabled>Reserver</md-button>
              </div>
              <md-card-expand-trigger>
                <md-button>Description</md-button>
              </md-card-expand-trigger>
            </md-card-actions>
            <md-card-expand-content>
              <md-card-content>
                {{book.description}}
              </md-card-content>
            </md-card-expand-content>
          </md-card-expand>
        </md-card>
      </div>
      <div v-if="afficheur === 'unEmpruntParLivre'">
      <p>Vous n'avez le droit qu'à un emprunt par livre.</p>
      </div>
      <div v-if="afficheur === 'maxEmprunt'">
      <p>Vous avez atteint votre limite de 3 emprunts.</p>
      </div>
      <div v-if="afficheur === 'empruntZeroLivre'">
      <p>Il n'y a plus d'exemplaire de cet ouvrage disponible. Veuillez lancer une nouvelle recherche pour
      effectuer une réservation.</p>
      </div>
      <div v-if="afficheur === 'maxReservation'">
      <p>Le montant maximum de reservation pour cet ouvrage a été atteint.</p>
      </div>
      <div v-if="afficheur === 'empruntPasres'">
      <p>Vous ne pouvez pas réserver un ouvrage que vous avez en cours d'emprunt.</p>
      </div>
      <div v-if="afficheur === 'resDejaEnCours'">
      <p>Vous avez déjà une réservation en cours pour ce livre.</p>
      </div>
      <div v-if="afficheur === 'maxResParUser'">
      <p>Vous avez déjà atteint votre maximum de 3 reservations.</p>
      </div>
      <div v-if="afficheur === 'livreDispo'">
      <p>Il y a un livre disponible. Relancez la recherche et empruntez le.</p>
      </div>
      <div v-if="afficheur === 'confirmationEmprunt'">
        <p> Veuillez confirmer votre emprunt:</p>
        <md-button class="md-dense md-raised md-accent" @click="createLoan(titreT), minorOne(titreT)">CONFIRMER</md-button>
      </div>
      <div v-if="afficheur === 'confirmationReservation'">
        <p>Veuiller confirmer votre réservartion:</p>
        <md-button class="md-dense md-raised md-accent" @click="createUres(titreTT)">CONFIRMER</md-button>
      </div>
      <div v-if="afficheur === 'emprunté'">
        <span id="messloaned"> Vous avez emprunter <strong>{{ loan.nomLivre }}</strong> jusqu'au <strong>{{ loan.finPret }}</strong>.</span>
      </div>
      <div v-if="afficheur === 'réservé'">
        <span id="messreserved"> Votre reservation de <strong>{{uRes.nomLivre}}</strong> est validé en date du {{uRes.dateCreation}}.</span>
      </div>
    </div>
  </div>
</template>

<script>import axios from 'axios'
export default {
  name: 'BookSearch',
  props: ['user'],
  data () {
    return {
      afficheur: '',
      titre: '',
      titreT: '',
      titreTT: '',
      auteur: '',
      intcountloans: '',
      intcountuRes: '',
      bookQuantite: null,
      loan: {},
      uRes: {}
    }
  },
  methods: {
    /* eslint-disable */
    searchbooks () {
      this.afficheur = "chargement"
      axios.get('http://localhost:8282/book-service/searchBooks/?titre=' + this.titre + '&auteur=' + this.auteur)
        .then(response => {
          this.afficheur = "afficherLivres"
          this.ListBooks = response.data
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    },
    createLoan (title) {
      this.afficheur = "chargement"
      axios.post('http://localhost:8282/loan-service/createLoan/?pseudoEmprunteur=' + this.user.pseudo + '&nomLivre=' + title)
        .then(response => {
          this.loan = response.data
          this.afficheur = "emprunté"
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    },
    countLoans (pseudo) {
      axios.post('http://localhost:8282/loan-service//CountLoansByUser/?pseudo=' + pseudo)
        .then(response => {
          this.intcountloans = response.data
        }, (response) => {
          console.log('erreur', response)
        })
    },
      countuRes (nomUtilisateur) {
          axios.get('http://localhost:8282/reservation-service//CountuResByUser/?nomUtilisateur=' + nomUtilisateur)
              .then(response => {
                  this.intcountuRes = response.data
              }, (response) => {
                  console.log('erreur', response)
              })
      },
      minorOne (titre) {
          axios.patch('http://localhost:8282/book-service/bookMinorOne/?titre=' + titre)
              .then(response => {
                  console.log('succes', response)
              }, (response) => {
                  console.log('erreur', response)
              })
      },
      createUres(nomLivre){
          this.afficheur = "chargement"
          axios.post('http://localhost:8282/reservation-service/addRes/?nomUtil=' + this.user.pseudo + '&nomLivre=' + nomLivre)
            .then(response => {
                console.log('succes', response)
                this.afficheur = "réservé"
                this.uRes = response.data
            }, (response) => {
                console.log('erreur', response)
            })
      },
      gestionEmprunt(NomLivre, nomUtil, bookQuantite){
          axios.get('http://localhost:8282/loan-service/ListeLoanByTitreAndUser/?pseudo=' + nomUtil + '&NomLivre=' + NomLivre)
              .then(response => {
                  this.ListLoansTitreUser = response.data
                  console.log('succes', response)
                  axios.get('http://localhost:8282/loan-service/ListLoans/?pseudo=' + nomUtil)
                      .then(response => {
                          this.ListLoansUser = response.data
                          console.log('succes', response)
                          if (this.ListLoansTitreUser.length === 1) {
                              this.afficheur = "unEmpruntParLivre"
                          } else if (this.ListLoansUser.length >= 3) {
                              this.afficheur = "maxEmprunt"
                          } else if (bookQuantite < 1) {
                              this.afficheur = "empruntZeroLivre"
                          } else {
                              this.afficheur = "confirmationEmprunt"
                              this.titreT = NomLivre
                          }
                      })
              })
      },
      gestionReservation(nomLivre, nomUtil, bookQtMax){
// fetch 1 liste emprunt par nomLivre et nomUtil
          axios.get('http://localhost:8282/loan-service/ListeLoanByTitreAndUser/?pseudo=' + nomUtil + '&NomLivre=' + nomLivre)
              .then(response => {
                  this.ListLoansTitreUser = response.data
                  console.log('succes1', response)
                  // fetch2 liste uReservation par nomLivre et nomUtil
                 axios.get('http://localhost:8282/reservation-service/listeResByNomlivreAndNomUtil/?nomLivre=' + nomLivre + '&nomUtil=' + nomUtil)
                     .then(response => {
                         this.listeResByNomlivreUtil = response.data
                         console.log('succes2', response)
                         // fetch3 liste uReservation par nomutil
                       axios.get('http://localhost:8282/reservation-service/FileAttente/?nomUtil=' + nomUtil)
                           .then(response => {
                               this.listResUser = response.data
                               console.log('succes3', response)
                               // fetch4 liste uReservation par nomlivre
                             axios.get('http://localhost:8282/reservation-service//listResNomLivre/?nomLivre=' + nomLivre)
                                 .then(response => {
                                    this.listeResNomlivre = response.data
                                     console.log('succes4', response)
                                  // fetch5 bookquantite
                                    axios.get('http://localhost:8282/book-service/getBookQuantity/?titre=' + nomLivre)
                                        .then(response => {
                                            this.bookQuantite = response.data
                                            console.log('succes5', response)
                                     if(this.bookQuantite > 0) {
                                         this.afficheur = "livreDispo"
                                     } else if(this.ListLoansTitreUser.length === 1){
                                         this.afficheur = "empruntPasres"
                                     } else if(this.listeResByNomlivreUtil.length === 1) {
                                         this.afficheur = "resDejaEnCours"
                                     } else if(this.listeResNomlivre.length >= (bookQtMax * 2)){
                                         this.afficheur = "maxReservation"
                                     } else if(this.listResUser.length === 3){
                                         this.afficheur = "maxResParUser"
                                     }
                                     else {
                                         this.afficheur = "confirmationReservation"
                                         this.titreTT = nomLivre
                                     }
                                        })
                                 })
                           })
                     })
              })
          }
      }
  }

</script>

<style scoped>
  #global-container{
    display: inline-block;
    width: 95%;
  }
  #booksContainer{
    width: fit-content;
  }
  #card-expanse{
    width: 220px;
    margin: 15px;
    display: inline-block;
    vertical-align: top;
  }
  #form-content{
    margin: 0 auto;
    width: 400px;
  }
  #form-search-books{
    margin: 40px;
  }
  #messloaned{
    margin: 20px;
  }
</style>
