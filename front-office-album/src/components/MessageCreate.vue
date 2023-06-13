<script>
import axios from "axios";
const BASE_API_URL = "http://localhost:8080/api/v1";

export default {
  data() {
    return {
      Messaggio: {
        email: "",
        messaggio: "",
      },
    };
  },
  methods: {
    sendMessage() {
      console.log(this.Messaggio);

      axios
        .post(BASE_API_URL + "/foto/message", this.Messaggio)
        .then((res) => {
          this.$router.push("/");
        })
        .catch((err) => console.log(err));
      this.Messaggio.email = "";
      this.Messaggio.messaggio = "";
    },
  },
};
</script>
<template lang="">
    <h2>Invia messaggio</h2>
    <form @submit.prevent="sendMessage">
        <label for="email">Email</label>
        <input type="email" name="email" v-model="Messaggio.email"><br>
        <label for="messaggio">messaggio</label>
        <input type="text" name="messaggio" v-model="Messaggio.messaggio"><br>
        <input type="submit" value="CREATE">
    </form>
</template>
<style lang="">
</style>