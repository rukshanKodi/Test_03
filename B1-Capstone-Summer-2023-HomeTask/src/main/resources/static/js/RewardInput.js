document.querySelectorAll('input[type="radio"][name="cash"]').forEach(e => {
    e.addEventListener('change', function() {
      if (this.value == "CASH") {
        cashExpanded.style.display = "block";
      } else {
        cashExpanded.style.display = "none";
      }
    })
  })