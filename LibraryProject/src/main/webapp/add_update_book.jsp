<%@ include file="header.jsp" %>

<div class="container">
	<% String formType = "update"; %>
	<c:if test="${ book != null }">
		<h1>Update Book</h1>
	</c:if>
	<c:if test = "${ book == null }">
		<h1>Add New Book</h1>
		<% formType = "add"; %>
	</c:if>
	<br><br>
	<form action="<%= formType %>" method="get" >
		 <div class="form-group">
	    <label for="isbn">Isbn</label>
	    <input type="text" class="form-control" id="isbn" name="isbn" 
	    	value="<c:out value='${ book.isbn }' />" required>
	  </div>
	  <div class="form-group">
	    <label for="title">Title</label>
	    <input type="text" class="form-control" id="title" name="title" 
	    	value="<c:out value='${ book.title }' />" required>
	  </div>
	  <div class="form-group">
	    <label for="description">Description</label>
	    <input type="text" class="form-control" id="description" name="description"
	    	value="<c:out value='${ book.descr }' />" required>  	
	  </div>
	  <div class="form-group">
	    <c:if test="${ book != null }">
			<%-- hidden input, won't show up on the page, but will pass our date_added for the book being updated--%>
			<% %><input type="hidden" name="added_to_library" value="<c:out value='${ book.added_to_library }' />">
		</c:if>
	  </div>
	  <div class="form-group">
	    <%-- <c:if test="${ book == null }">
	    TODO: ERROR: Null pointer here
			-- hidden input, won't show up on the page, but will pass our id for the product --
			<input type="hidden" name="rented" value="<c:out value='${ book.rented }' />">
		</c:if>--%>
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
<%@ include file="footer.jsp" %>