<template>
  <v-container class="fill-height">
    <v-responsive class="d-flex align-center text-center fill-height">

      <h1 v-for="user in appStore.users" :key="user.id">
        <span>{{ user.id }}.</span>  {{ user.firstName  }}
      </h1>

    </v-responsive>
  </v-container>
</template>

<script>
import { useAppStore } from '@/store/appStore'

export default {
  setup() {
    const appStore = useAppStore()
    return {
      appStore
    }
  },
  mounted() {
    this.appStore.getUsers()
  }
}

</script>
