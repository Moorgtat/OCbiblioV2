<template>
  <div id="BookCreate">
    <h2>J'ajoute un livre</h2>
    <p>Si tu veux faire don d'un de tes livres à la bibliothèque, <br> enregistre le pour que d'autres
      personnes puissent le lire.</p>
    <div class="md-content" id="form-content">
      <form v-if="!submitted" id="form-create-books" class="form-group label-floating">
        <div class="md-input-container">
          <md-field>
            <label>Titre</label>
            <md-input id="titre" name="titre" type="text" v-model="book.titre"></md-input>
            <span class="md-helper-text">Indiquez le titre du livre.</span>
          </md-field>
          <md-field>
            <label>Description</label>
            <md-textarea id="description" name="description" type="text" v-model="book.description"></md-textarea>
            <span class="md-helper-text">Résumé du livre.</span>
          </md-field>
          <md-field>
            <label>Auteur</label>
            <md-input id="auteur" name="auteur" type="text" v-model="book.auteur"></md-input>
            <span class="md-helper-text">L'auteur du livre.</span>
          </md-field>
          <md-field>
            <md-input id="file" name="file" type="file" v-model="book.file" @change="onFileSelected"></md-input>
            <span class="md-helper-text">Image de la couverture.</span>
          </md-field>
          <md-button class="md-raised md-default" @click="createbook">Créer</md-button>
        </div>
      </form>
      <div id="NewBook">
        <div v-if="submitted">
          <span id="confirmBook"> <strong>Félicitation, vous avez créer un nouveau livre:</strong></span>
          <md-card id="card-expanse">
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
                  <md-button>Emprunter</md-button>
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
          <md-button id="Retour" class="md-raised" @click="retour" href="#create">Ajouter un autre livre</md-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>import axios from 'axios'
export default {
  name: 'BookCreate',
  data () {
    return {
      selectedFile: null,
      book: {
        titre: '',
        description: '',
        auteur: '',
        file: null,
        image: ''
      },
      submitted: false
    }
  },
  methods: {
    /* eslint-disable no-console */
    onFileSelected (event) {
      console.log(event)
      this.selectedFile = event.target.files[0]
    },
    createbook () {
      let image = this.selectedFile.name
      axios.post('http://localhost:8282/book-service/createBook?titre=' + this.book.titre + '&description=' + this.book.description + '&auteur=' + this.book.auteur + '&image=' + image)
        .then(response => {
          this.book = response.data
          console.log(response.data)
          this.submitted = true
        }).catch(e => {
          console.log('erreur', e)
        })
    },
    retour () {
      this.book.titre = ''
      this.book.auteur = ''
      this.book.description = ''
      this.book.file = null
      this.book.image = ''
      this.submitted = false
    }
  }
}
</script>

<style scoped>
  #form-content{
    margin: 0 auto;
    width: 400px;
  }
  #form-create-books{
    margin: 30px;
  }
  #Retour{
    float: right;
  }
  #card-expanse{
    margin: 20px;
  }
  #BookCreate{
    margin: 20px;
  }
</style>
