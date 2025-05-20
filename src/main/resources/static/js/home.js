document.addEventListener("DOMContentLoaded", function(event) {
    tokenVerification();
});

function tokenVerification() {

    if (typeof Cookies.get('token') === 'undefined') {
        console.log("Cookie not detected");
        console.log(Cookies.get('token'));
        document.location.href="index.html";
    }
    console.log("Cookie detected");
}
