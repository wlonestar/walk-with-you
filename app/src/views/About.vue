<template>
  <div class="about">
    <h1>This is an about page</h1>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { getAllUser } from '../api/user.ts'
export default defineComponent({
  name: 'About',
  data () {
    return {
      user: {}
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getAllUser().then(response => {
        console.log(response)
      })
    }
  }
})
</script>

<style scoped>

</style>
