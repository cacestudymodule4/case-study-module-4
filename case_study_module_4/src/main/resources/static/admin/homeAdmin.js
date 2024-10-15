// add hovered class to selected list item

let list = document.querySelectorAll(".navigation li");

function activeLink() {
    list.forEach(item => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered")
}

list.forEach(item => item.addEventListener("mouseover", activeLink));

//menu toggle

let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};

function confirmDelete(event) {
    const toast = document.getElementById('toast');
    toast.classList.add('show'); // Show the toast notification

    // Prevent form submission initially
    event.preventDefault();

    // Use a timeout to simulate confirmation logic
    setTimeout(() => {
        if (confirm('Are you sure you want to delete this?')) {
            event.target.submit(); // Submit the form if confirmed
        }
        toast.classList.remove('show'); // Hide the toast after confirmation
    }, 500); // Adjust timeout as needed

    return false;
}