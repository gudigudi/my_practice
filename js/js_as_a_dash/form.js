$(function() {
  $("form").submit(function() {
    var gadget = $("#gadget").val();
    alert("Go Go gadget " + gadget);
    return false;
  });
});
