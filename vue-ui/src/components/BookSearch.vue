<template>
  <div id='BookSearch'>
    <h2>Je cherche un livre</h2>
     <p>Tous nos livres sont disponible ici {{user.pseudo}}! Clique sur chercher<br>
       pour tous les afficher ou lance une recherche par Titre et par Auteur. <br>
       Tu peux emprunter jusque 5 livres en cliquant sur Emprunter.</p>
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
        <md-button class="md-raised md-default" @click="searchbooks(); countLoans(user.pseudo);">Chercher</md-button>
      </form>
    </div>

    <div id="global-container">
      <div v-if="searched" class="md-content" id="booksContainer">
        <md-card id="card-expanse" v-for="book in ListBooks" :key="book.id">
          <md-card-media>
            <img :src='book.image' alt="couverture du livre">
          </md-card-media>
          <md-card-header>
            <div class="md-title">{{book.titre}}</div>
            <div class="md-subhead">{{book.auteur}}</div>
          </md-card-header>
          <md-card-expand>
            <md-card-actions md-alignment="space-between">
              <div>
                <md-button v-if="intcountloans <= 4" href="#find" @click="createLoan(book.titre)">Emprunter</md-button>
                <md-button v-if="intcountloans == 5" class="md-accent" disabled>Emprunter</md-button>
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
      <div v-if="loaned">
        <span id="messloaned"> Vous avez emprunter <strong>{{ loan.nomLivre }}</strong> jusqu'au <strong>{{ loan.finPret }}</strong>.</span>
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
      searched: false,
      loaned: false,
      titre: '',
      auteur: '',
      ListBooks: [],
      nomLivre: '',
      intcountloans: '',
      loan: {}
    }
  },
  methods: {
    /* eslint-disable no-console */
    searchbooks () {
      this.loaned = false
      this.searched = false
      axios.get('http://localhost:8282/book-service/searchBooks/?titre=' + this.titre + '&auteur=' + this.auteur)
        .then(response => {
          this.searched = true
          this.ListBooks = response.data
          console.log('succes', response)
        }, (response) => {
          console.log('erreur', response)
        })
    },
    createLoan (title) {
      axios.post('http://localhost:8282/loan-service/createLoan/?pseudoEmprunteur=' + this.user.pseudo + '&nomLivre=' + title)
        .then(response => {
          this.loan = response.data
          this.searched = false
          this.loaned = true
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
