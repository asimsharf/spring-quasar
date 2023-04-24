import { defineStore } from 'pinia'

export const useAppStore = defineStore('useAppStore', {

  state: () => ({
    users: [],
  }),


  actions: {
    async getUsers() {
      const response = await fetch('http://localhost:3000/api/v1/users')
     console.log(response)
     console.log(response.json())
     console.log(response.json().then(data => console.log(data)))
     console.log(response.json().then(data => this.users = data));


    }
  },

  getters: {
    usersCount() {
      return this.users.length;
    },
  },

});
