<table>
    <tr>
        <td>f1</td>
        <td>f2</td>
    </tr>
    <% for(r in data) { %>
    <tr>
        <td><%= r.f1 %></td>
        <td><%= r.f2 %></td>
    </tr>
    <% } %>
</table>