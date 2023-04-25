<template>
  <v-container class="fill-height">
    <v-responsive class="d-flex align-center text-center fill-height">

      <h1 v-for="user in store.users" :key="user.id">
        <span>{{ user.id }}.</span> {{ user.firstName }}
        <span>{{ user.lastName }}</span>
        <span>{{ user.email }}</span>
      </h1>



    </v-responsive>
  </v-container>
</template>

<script>

import { useAppStore } from '@/store/appStore'

export default {
  setup() {
    const store = useAppStore()
    return {
      store
    }
  },
  mounted() {
    this.store.getUsers();
  }
}

</script>
