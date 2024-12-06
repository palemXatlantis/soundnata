<%-- 
    Document   : register
    Created on : Dec 5, 2024, 10:58:15 PM
    Author     : Leptop
--%>
<%
    String authenticatedUser = (String) session.getAttribute("authenticatedUser");
    if(authenticatedUser != null){
        response.sendRedirect("index.jsp");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SoundNata</title>
        <link
            href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
            rel="stylesheet"
            />
    </head>

    <body class="bg-black text-white">
      <%@include file="WEB-INF/nav_auth.jsp" %>


        <div class="flex items-center justify-center min-h-screen">
            <div class="bg-gray-400 shadow-md rounded-lg p-8 w-full max-w-md">
                <h1 class="text-3xl font-bold mb-6 text-center">Register</h1>
                <form class="flex flex-col space-y-4" method="POST" action="Register">
                    <div>
                        <label for="email" class="block mb-2 text-sm font-medium"
                               >Email</label
                        >
                        <input
                            type="email"
                            id="email"
                            name="email"
                            class="p-2 w-full rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                            placeholder="Masukkan email kamu.."
                            required
                            />
                    </div>

                    <div>
                        <label for="username" class="block mb-2 text-sm font-medium"
                               >Username</label
                        >
                        <input
                            type="text"
                            id="username"
                            name="username"
                            class="p-2 w-full rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                            placeholder="Masukkan username kamu..."
                            required
                            />
                    </div>

                    <div>
                        <label for="name" class="block mb-2 text-sm font-medium"
                               >Nama</label
                        >
                        <input
                            type="text"
                            id="name"
                            name="name"
                            class="p-2 w-full rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                            placeholder="Masukkan nama kamu..."
                            required
                            />
                    </div>

                    <div>
                        <label for="password" class="block mb-2 text-sm font-medium"
                               >Password</label
                        >
                        <input
                            type="password"
                            id="password"
                            name="password"
                            class="p-2 w-full rounded border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 text-black"
                            placeholder="Masukkan password kamu...."
                            required
                            />
                    </div>

                    <div class="flex justify-center mt-8">
                        <button
                            class="bg-gray-500 px-5 py-3 rounded-full text-2xl text-center"
                            type="submit"
                            >
                            Create
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
