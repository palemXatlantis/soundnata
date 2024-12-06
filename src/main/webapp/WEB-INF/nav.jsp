<nav class="bg-gray-800 p-4 fixed w-full z-10 top-0">
    <div class="container mx-auto flex justify-between items-center">
        <div class="flex items-center">
            <h1 class="text-3xl font-bold">
                <a href="index.jsp" class="hover:text-gray-400">
                    Sound<span class="text-blue-500">Nata</span>
                </a>
            </h1>

            <div class="ml-6 relative">
                <input
                    type="text"
                    placeholder="Search for songs, artists..."
                    class="px-4 py-2 rounded-lg bg-gray-700 text-white focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
            </div>
        </div>

        <div class="flex items-center space-x-4">
            <%
                String username = (session != null) ? (String) session.getAttribute("authenticatedUser") : null;

                if (username != null) {
            %>

            <a href="login.jsp" class="hover:text-gray-400"><%= username%></a>
            <a href="Logout" class="hover:text-gray-400">logout</a>
            <% } else {%>
            <a href="login.jsp" class="hover:text-gray-400">login</a>
            <a href="register.jsp" class="hover:text-gray-400">Register</a>

            <% }%>

        </div>
    </div>
</nav>