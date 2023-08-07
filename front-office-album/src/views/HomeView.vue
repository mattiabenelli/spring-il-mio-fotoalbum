<script>
const BASE_API_URL = "http://localhost:8080/api/v1";
import axios from "axios";

import MessageCreate from "../components/MessageCreate.vue";

export default {
  data() {
    return {
      foto: [],
      inputSearch: "",
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
    searchImage() {
      axios
        .get(BASE_API_URL + "/foto/search?titolo=" + this.inputSearch)
        .then((res) => {
          this.foto = res.data;
          console.log(this.foto);
        })
        .catch((err) => console.log(err));
    },
  },
  components: {
    MessageCreate,
  },
  mounted() {
    this.getImage();
  },
  computed: {
    filteredFoto() {
      return this.foto.filter((item) => item.visibile);
    },
  },
};
</script>

<template>
  <main class="container mt-4">
    <div class="row justify-content-center">
      <div class="col-md-8 text-center">
        <h3>Cerca una foto</h3>
        <div class="form-group d-flex">
          <input
            type="text"
            name="search"
            v-model="inputSearch"
            class="form-control"
          />
          <button @click="searchImage()" class="btn btn-primary ml-2 ms-2">
            Cerca
          </button>
        </div>
      </div>
    </div>
    <div class="row mt-4 justify-content-center">
      <div class="col-md-8">
        <ul class="list-group">
          <li
            v-for="item in filteredFoto"
            :key="item.id"
            class="list-group-item"
          >
            <div class="d-flex flex-column align-items-center">
              <img
                :src="item.url"
                class="img-thumbnail mb-3"
                style="max-width: 300px; max-height: 100%"
              />
              <span class="text-center">{{ item.titolo }}</span>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <MessageCreate />
  </main>
</template>







