<template>
  <div id="Navbar">

    <h1>Oc'Biblio</h1>

    <md-menu md-align-trigger>
      <md-button md-menu-trigger id='boutonMenuHome' href="#presentation" style='text-decoration:none'>
        <strong>Home</strong></md-button>
    </md-menu>

    <md-menu md-direction='bottom-start'>
      <md-button md-menu-trigger><strong>Nos Livres</strong></md-button>
      <md-menu-content>
        <md-menu-item href="#find">Trouver un Livre</md-menu-item>
        <md-menu-item  href="#create">Ajouter un Livre</md-menu-item>
      </md-menu-content>
    </md-menu>

    <md-menu md-direction='bottom-start'>
      <md-button md-menu-trigger href="#infos"><strong>Profil</strong></md-button>
    </md-menu>
  </div>
</template>

<script>export default {name: 'Navbar'}
</script>

<style scoped>
  strong {
    font-size: medium;
    font-family: "Calibri Light";
  }
  .md-menu {
    margin: 10px;
  }
  .md-menu-item{
    font-family: "Calibri Light";
  }
  h1{
    font-family: "Yu Gothic UI";
    font-size: xx-large;
  }
</style>
