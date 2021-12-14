<template>
  <div>
    <Nav></Nav>
    <div>
      <div class="timeline">
        <div>
          <img src="../assets/img/标题二.png" class="subtitle1" alt="">
        </div>
        <div class="timeline">
          <div class="cards">
            <section class="card" v-for="event in events" :key="event.id">
              <header class="card__header">
                <h2 class="header__title">
                  <span class="title__date">{{ event.time }}</span>
                  <span class="title__sub">{{ event.name }}</span>
                </h2>
              </header>
              <article class="card__article">
                <p>{{ event.introduction }}</p>
              </article>
            </section>
            <div style="height: 30px;"></div>
          </div>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { getAllEvents } from '../api/event.ts'
import Nav from '@/components/Nav.vue'
import Footer from '@/components/Footer.vue'

export default defineComponent({
  name: 'Event',
  components: { Footer, Nav },
  data () {
    return {
      events: [
        {
          id: 0,
          name: '',
          time: '',
          location: '',
          introduction: ''
        }
      ]
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getAllEvents().then(response => {
        console.log(response.status)
        this.events = response.data
      })
    }
  }
})
</script>

<style scoped>
:root {
  --bg-color: #10aec2;
}
.cards {
  position: relative;
  display: flex;
  flex-direction: column;
  width: 90%;
  margin-left: 5%;
  height: 100%;
  padding: 10px 0;
}
.cards::after {
  z-index: 9;
  content: "";
  position: absolute;
  top: 0;
  bottom: 0;
  left: 50%;
  border-left: 1px solid rgba(200,200,200,0.5);
}
.card {
  position: relative;
  width: 45%;
  height: 250px;
  display: flex;
  flex-direction: column;
  /*box-shadow: 0 3px 1px -2px rgba(0,0,0,0.2), 0 2px 2px 0 rgba(0,0,0,0.14), 0 1px 5px 0 rgba(0,0,0,0.12);*/
}
.card:nth-child(odd) {
  align-self: flex-start;
}
.card:nth-child(even ) {
  align-self: flex-end;
}
.card__header {
  display: flex;
  align-items: center;
  width: 100%;
  height: 35%;
  color: #fff;
  background-color: #F25B5A;
}
.card:nth-child(odd) .card__header::after {
  position: absolute;
  left: 100%;
  content: "";
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 10px 0 10px 18px;
  border-color: transparent transparent transparent #F25B5A;
}
.card:nth-child(even) .card__header::after {
  position: absolute;
  right: 100%;
  content: "";
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 10px 18px 10px 0;
  border-color: transparent #F25B5A transparent transparent;
}
.card:nth-child(odd) .card__header::before {
  z-index: 10;
  position: absolute;
  left: calc(111.11% - 3px);
  content: "";
  width: 10px;
  height: 10px;
  border-radius: 10px;
  background-color: #bdbdbd;
  box-shadow: 0 0 2px 6px #fff;
}
.card:nth-child(even) .card__header::before {
  z-index: 10;
  position: absolute;
  right: calc(111.11% - 5px);
  content: "";
  width: 10px;
  height: 10px;
  border-radius: 10px;
  background-color: #bdbdbd;
  box-shadow: 0 0 2px 6px #fff;
}
.header__title {
  display: flex;
  margin-left: 10px;
  margin-top: 5px;
  margin-bottom: 5px;
  flex-direction: column;
  font-size: 0.6rem;
}
.title__sub {
  padding-top: 6px;
  font-size: 1.2rem;
}
.card__article {
  width: 100%;
  height: 150%;
  background-color: #fff;
  border: 1px solid rgba(200,200,200,0.5);
  border-top: none;
  padding: 10px;
}
</style>
