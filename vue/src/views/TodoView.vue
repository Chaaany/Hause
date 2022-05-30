<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div class="mb-3">
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <!-- Text slides with image -->
        <!-- Slides with custom text -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              src="https://picsum.photos/1024/480/?image=84"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              src="https://picsum.photos/1024/480/?image=70"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              src="https://picsum.photos/1024/480/?image=53"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>
    <h3 class="underline-steelblue">
      <b-icon icon="calendar-check"></b-icon> Todo List

      <b-spinner variant="info" label="Spinning"></b-spinner>
      <b-spinner variant="info" type="grow" label="Spinning"></b-spinner>
    </h3>
    <b-row>
      <b-col>
        <todo-header></todo-header>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <todo-input></todo-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <todo-list></todo-list>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import TodoHeader from "@/components/todo/TodoHeader.vue";
import TodoInput from "@/components/todo/TodoInput.vue";
import TodoList from "@/components/todo/TodoList.vue";

export default {
  name: "TodoView",
  components: {
    TodoHeader,
    TodoInput,
    TodoList,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.img-fluid {
  max-width: 100%;
  height: 100px;
}
</style>
