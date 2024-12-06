<%-- 
    Document   : home
    Created on : Dec 5, 2024, 8:01:34 PM
    Author     : Leptop
--%>
<%
    String authenticatedUser = (String) session.getAttribute("authenticatedUser");
    if(authenticatedUser == null){
        response.sendRedirect("login.jsp");
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
      <%@include file="WEB-INF/nav.jsp" %>

    <div class="flex pt-20">
      <aside class="bg-gray-900 w-64 p-6 fixed h-screen">
        <ul class="space-y-4">
          <li>
            <a href="#" class="text-xl hover:text-blue-500">Liked Songs</a>
          </li>
          <li>
            <a href="#" class="text-xl hover:text-blue-500">Playlists</a>
          </li>
        </ul>

        <div class="mt-8">
        <div class="flex justify-between items-center">
            <h2 class="text-lg font-bold mb-4">Playlists</h2>
        <button
        onclick="window.location.href='tambahplaylist.html'"
         class=" text-white py-2 px-4 text-2xl"
        >
      +
        </button>
    </div>
</div>
</div>
      </aside>
        
      <main class="flex-1 ml-72 p-8 space-y-8">
        <section class="bg-gray-700 rounded-lg p-6">
          <h2 class="text-2xl font-bold mb-4">Music</h2>
          <div class="grid grid-cols-5 gap-4">
            <div class="bg-gray-800 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <p class="text-center"></p>
            </div>
            <div class="bg-gray-800 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <p class="text-center"></p>
            </div>
            <div class="bg-gray-800 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <p class="text-center"></p>
            </div>
            <div class="bg-gray-800 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <p class="text-center"></p>
            </div>
            <div class="bg-gray-800 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <p class="text-center"></p>
            </div>
          </div>
        </section>

        <section>
          <h2 class="text-2xl font-bold mb-4">Recommended Playlists</h2>
          <div class="grid grid-cols-3 gap-6">
            <div class="bg-gray-700 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <h3 class="font-bold">Relax</h3>
              <p class="text-sm">Various Artists</p>
            </div>
            <div class="bg-gray-700 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <h3 class="font-bold">Top Hits</h3>
              <p class="text-sm">Top Artists</p>
            </div>
            <div class="bg-gray-700 p-4 rounded-lg">
              <img
                src="postpct.jpeg"
                alt="Example Image"
                class="w-64 h-48 rounded-lg"
              />

              <h3 class="font-bold">Rock Classics</h3>
              <p class="text-sm">Best Rock Songs</p>
            </div>
          </div>
        </section>
      </main>
    </div>

    <footer class="bg-gray-800 p-2 fixed bottom-0 w-full">
      <div class="container mx-auto flex justify-between items-center">
        <div class="flex items-center space-x-3">
          <img
            src="postpct.jpeg"
            alt="Example Image"
            class="w-20 h-20 rounded-lg"
          />

          <div>
            <p class="font-bold">Circle</p>
            <p class="text-sm text-gray-400">Post Malone</p>
          </div>
        </div>
        <div class="flex items-center space-x-3">
          <button class="text-white hover:text-blue-500">Previous</button>
          <button class="text-white hover:text-blue-500">Next</button>
          <button class="text-white hover:text-blue-500">Double Next</button>
        </div>
        <div>
          <input type="range" class="w-16" min="0" max="100" />
        </div>
      </div>
    </footer>
  </body>
</html>
