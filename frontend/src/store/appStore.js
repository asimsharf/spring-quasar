import { defineStore } from 'pinia'
import axios from "axios"

export const useAppStore = defineStore('useAppStore', {

  state: () => ({
    users: [],
  }),

  actions: {
    async getUsers() {
      try {
        await axios.get('/api/v1/users').then((response) => {
          console.log(response.data.data)
          this.users = response.data.data
        })
      }
      catch (error) {
        console.log(error)
      }
    }
  },
});
