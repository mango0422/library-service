function Showloaned() {
    var con1 = document.getElementById("loantable");
    var con2 = document.getElementById("reservetable");
    var loantitle = document.getElementById("loanbtn");
    var reservetitle = document.getElementById("reservebtn");
    var loananchor = document.getElementById("loana");
    var reserveanchor = document.getElementById("reservea");
    var selectloaneddate = document.getElementById("select2loaneddate");
    var loanbutton = document.querySelector("div.submitbutton button");
    con1.style.display = 'flex';
    con2.style.display = 'none';
    loantitle.style.backgroundColor = "#004593";
    loantitle.style.color = "white";
    reservetitle.style.backgroundColor = "white";
    loananchor.style.color = "white"
    reserveanchor.style.color = "black"
    selectloaneddate.textContent = "대출일";
    loanbutton.textContent = "대출취소";


}
function Showreserved() {
    var con1 = document.getElementById("loantable");
    var con2 = document.getElementById("reservetable");
    var loananchor = document.getElementById("loana");
    var reserveanchor = document.getElementById("reservea");
    var selectloaneddate = document.getElementById("select2loaneddate");
    var loanbutton = document.querySelector("div.submitbutton button");
    var loantitle = document.getElementById("loanbtn");
    var reservetitle = document.getElementById("reservebtn");
    con1.style.display = 'none';
    con2.style.display = 'flex';

    loantitle.style.backgroundColor = "white";
    reservetitle.style.backgroundColor = "#004593";
    loananchor.style.color = "black"
    reserveanchor.style.color = "white"
    selectloaneddate.textContent = "예약일";
    loanbutton.textContent = "예약취소";
}

window.onload = Showloaned;