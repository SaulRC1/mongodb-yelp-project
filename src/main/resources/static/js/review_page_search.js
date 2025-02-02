function moveToFirstPage()
{
    searchText = document.getElementById("used-search-text").textContent;

    window.location.href = "/review-search?page=1&searchText=" + searchText;
}

function moveBackwardsPage()
{
    paginationPageDisplay = document.getElementsByClassName("pagination-page-display")[0].textContent;
    currentPage = paginationPageDisplay.split(" ")[0];
    
    if(currentPage === "1")
    {
        return;
    }
    
    backwardsPage = Number(currentPage) - 1;
    
    searchText = document.getElementById("used-search-text").textContent;

    window.location.href = "/review-search?page=" + backwardsPage + "&searchText=" + searchText;
}

function moveForwardPage()
{
    paginationPageDisplay = document.getElementsByClassName("pagination-page-display")[0].textContent;
    
    currentPage = paginationPageDisplay.split(" ")[0];
    lastPage = Number(paginationPageDisplay.split(" ")[2]);
    
    forwardPage = Number(currentPage) + 1;
    
    if(forwardPage > lastPage)
    {
        return;
    }
    
    searchText = document.getElementById("used-search-text").textContent;
    
    window.location.href = "/review-search?page=" + forwardPage + "&searchText=" + searchText;
}

function moveToLastPage()
{
    searchText = document.getElementById("used-search-text").textContent;
    
    window.location.href = "/review-search?page=last&searchText=" + searchText;
}

function searchReview()
{
    let searchTextField = document.getElementsByClassName("search-field-text-input")[0];
    
    if(typeof searchTextField.value === "undefined")
    {
        alert("Por favor introduzca una palabra/frase antes de búscar");
        return;
    }
    
    let searchText = searchTextField.value.trim();
    
    if(searchText.length <= 0)
    {
        alert("Por favor introduzca una palabra/frase antes de búscar");
        return;
    }
    
    window.location.href = "/review-search?page=1&searchText=" + searchText;
}
