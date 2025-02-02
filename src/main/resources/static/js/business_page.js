function moveToFirstPage()
{
    window.location.href = "/businesses?page=1";
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
    
    window.location.href = "/businesses?page=" + backwardsPage;
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
    
    window.location.href = "/businesses?page=" + forwardPage;
}

function moveToLastPage()
{
    window.location.href = "/businesses?page=last";
}
