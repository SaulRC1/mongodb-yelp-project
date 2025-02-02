function moveToFirstPage()
{
    window.location.href = "/reviews?page=1";
}

function moveBackwardsPage()
{
    reviewList = document.getElementsByClassName("review-card");
    
    firstReview = reviewList[0];
    
    date = firstReview.getElementsByClassName("review-card-date-hidden")[0].textContent;
    
    paginationPageDisplay = document.getElementsByClassName("pagination-page-display")[0].textContent;
    
    currentPage = paginationPageDisplay.split(" ")[0];
    
    if(currentPage === "1")
    {
        return;
    }
    
    backwardsPage = Number(currentPage) - 1;
    
    window.location.href = "/reviews?page=" + backwardsPage + "&date=" + date + "&backwards=true";
}

function moveForwardPage()
{
    reviewList = document.getElementsByClassName("review-card");
    
    lastReview = reviewList[reviewList.length - 1];
    
    date = lastReview.getElementsByClassName("review-card-date-hidden")[0].textContent;
    
    paginationPageDisplay = document.getElementsByClassName("pagination-page-display")[0].textContent;
    
    currentPage = paginationPageDisplay.split(" ")[0];
    lastPage = Number(paginationPageDisplay.split(" ")[2]);
    
    forwardPage = Number(currentPage) + 1;
    
    if(forwardPage > lastPage)
    {
        return;
    }
    
    window.location.href = "/reviews?page=" + forwardPage + "&date=" + date + "&backwards=false";
}

function moveToLastPage()
{
    window.location.href = "/reviews?page=last";
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
