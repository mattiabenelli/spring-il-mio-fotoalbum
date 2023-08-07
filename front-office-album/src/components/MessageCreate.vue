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
<template>
  <div class="container col-md-8 my-4">
    <h2>Invia messaggio</h2>
    <form @submit.prevent="sendMessage">
      <div class="form-group">
        <label for="email">Email</label>
        <input
          type="email"
          name="email"
          v-model="Messaggio.email"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="messaggio">Messaggio</label>
        <input
          type="text"
          name="messaggio"
          v-model="Messaggio.messaggio"
          class="form-control"
        />
      </div>
      <input type="submit" value="CREATE" class="btn btn-primary mt-2" />
    </form>
  </div>
</template>
<style lang="">
</style>