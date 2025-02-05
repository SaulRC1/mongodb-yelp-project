function moveToFirstPage()
{
    let currentUserId = document.getElementById("current-user-id").textContent;
    window.location.href = "/user-information/reviews/" + currentUserId + "?page=1";
}

function moveBackwardsPage()
{
    let currentBusinessId = document.getElementById("current-business-id").textContent;
    
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
    
    window.location.href = "/business-information/" + currentBusinessId + "?page=" + backwardsPage
            + "&date=" + date + "&backwards=true";
}

function moveForwardPage()
{
    let currentBusinessId = document.getElementById("current-business-id").textContent;
    
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
    
    window.location.href = "/business-information/" + currentBusinessId + "?page=" + forwardPage
            + "&date=" + date + "&backwards=false";
}

function moveToLastPage()
{
    let currentBusinessId = document.getElementById("current-business-id").textContent;
    window.location.href = "/business-information/" + currentBusinessId + "?page=last";
}
