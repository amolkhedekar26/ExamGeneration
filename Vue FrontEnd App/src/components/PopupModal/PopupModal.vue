<template>
  <modal name="my-first-modal"> This is my first modal </modal>
</template>

<script>
export default {
  name: "PopupModal",
  methods: {
    show() {
      this.$modal.show("my-first-modal");
    },
    hide() {
      this.$modal.hide("my-first-modal");
    },
  },
  mounted(){
      this.show()
  }
};
</script>

<style>
</style>