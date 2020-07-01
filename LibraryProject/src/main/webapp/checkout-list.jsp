<%--@ include file="header.jsp" --%>

<h1>Your Books History</h1>
	
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>ID #</th>
				<th>ISBN</th>
				<th>Title</th>
				<th>Checked Out </th>
				<th>Due Date</th>
				<th>Return Date</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="checkedout" items="${ checkedoutBooks }">
				
				<tr>
					<td>
						<c:out value="${ checkedout.checkoutId }" />
					</td>
					
					<td>
						<c:out value="${ checkedout.isbn }" />
					</td>
					
					<td>
						<c:out value="${ checkedout.bookTitle }" />
					</td>
					
					<td>
						<c:out value="${ checkedout.checkedout }" />
					</td>
					
					<td>
						<c:out value="${ checkedout.dueDate }" />
					</td>
					
					<td>
						<c:out value="${ checkedout.returnDate }" />
					</td>
					
				</tr>
				
			</c:forEach>	
		
		</tbody>
		
			
	</table>

<%--@ include file="footer.jsp" --%>