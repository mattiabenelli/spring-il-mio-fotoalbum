<script>
const BASE_API_URL = "http://localhost:8080/api/v1";
import axios from "axios";

export default {
  data() {
    return {
      foto: [],
    };
  },
  methods: {
    getImage() {
      axios
        .get(BASE_API_URL + "/foto")
        .then((res) => {
          this.foto = res.data;
          console.log(this.foto);
        })
        .catch((err) => console.log(err));
    },
  },
  mounted() {
    this.getImage();
  },
};
</script>

<template>
  <main>
    <ul>
      <li v-for="item in foto" :key="item.id">{{ item.titolo }}</li>
    </ul>
  </main>
</template>
