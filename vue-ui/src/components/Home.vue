<template>
  <div id="home">
    <header>
      <navbar/>
    </header>
    <div id="presentation">
      <h2> Bonjour {{ $parent.user.pseudo }}</h2>
    <p>Tu es en manque de lecture.</p>
    <p>Tu viens sur le site de la bibliothèque pour trouver un livre que tu pourrais
      emprunter.</p>
    <p>Avec la possibilité de prolonger le prêt si tu n'as pas eu le temps de le lire.</p>
    <p> Et de rendre le livre que tu as emprunté.</p>
    <p>Mais aussi de donner un livre à la bibliothèque en l'ajoutant.</p>
      <p>Bonne visite!</p>
    </div>
    <div id="find">
    <book-search :user="{ pseudo: $parent.user.pseudo, mail: $parent.user.mail}"/>
    </div>
    <div id="create">
    <book-create/>
    </div>
    <div id="infos">
    <user-info :user="{ pseudo: $parent.user.pseudo, mail: $parent.user.mail}"/>
    </div>
  </div>
</template>

<script>
import Navbar from './Navbar'
import BookSearch from './BookSearch'
import BookCreate from './BookCreate'
import UserInfo from './UserInfo'
export default {
  name: 'Home',
  components: {UserInfo, BookCreate, BookSearch, Navbar}}
</script>

<style scoped>
  #presentation{
    padding: 40px;
  }
  #find{
    padding: 40px;
  }
  #create{
    padding: 40px;
  }
  #infos{
    height: 400px;
    padding: 40px;
    margin-bottom: 320px;
  }
</style>
