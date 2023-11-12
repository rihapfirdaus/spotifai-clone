# SPOTIFAI CLONE

submission project for the course "object oriented programming"

- Web Service
- Object Oriented Programming
- SOLID Principle
- Design Pattern MVC
- Database Connection
- Graphic User Interface

### About

Proyek Spotify Clone menggunakan Java dengan Gradle non-modular; GUI menggunakan JavaFX, FXML dan CSS; Webservice menggunakan SpringBoot dan Jackson; Database menggunakan Spring Jdbc dan Server MySQL yang bisa diakses melalui db4free.net; Proyek ini juga menyimpan Login Session menggunakan Spring Session dengan Regis.

Sampai saat ini Poyek sudah bisa melakukan validasi login dan signup dengan pendekatan webservice, mendeteksi file musik yang ada di database, memutarnya dan berpindah antar lagu, serta mengatur volume dari lagu. Proyek ini akan terus berkembang.

### How To Run

you can run from main class 'com.apps.spotifai.StockUiApplication'

### Output

- Login page
  ![Login](./assets/UI-Login.png)

- Signup page
  ![Signup](./assets/UI-Signup.png)

- Dashboard
  ![Dashboard](./assets/UI-Dashboard.png)

### Use Case

1. Use case User
   - bisa membuat akun
   - bisa menggunakan username yang belum digunakan oleh user lain
   - bisa bebas memilih nama akun
   - bisa bebas menambahkan informasi-informasi akun lainnya, seperti foto profil dan tanggal lahir
   - bisa mengakses aplikasi menggunakan akun
   - bisa mengubah password ketika lupa atau ketika diinginkan
   - bisa mengubah semua informasi-informasi pada akun
   - bisa melihat lagu-lagu yang tersedia
   - bisa menambahkan lagu menjadi favorit
   - bisa membuat playlist dan menambahkan lagu kedalamnya
   - bisa melihat lagu yang difavoritkan
   - bisa melihat playlist lagu yang telah dibuatnya atau dibuat orang lain
   - bisa mencari lagu sesuai yang diinginkan berdasarkan judul, artist, ataupun potongan lirik
   - bisa memutar lagu yang diinginkan
   - bisa berpindah antar lagu (sebelum/sesudah)
   - bisa melihat lirik lagu yang sedang diputar apabila tersedia
   - bisa melihat informasi lagu seperti nama artis, album
   - bisa menghentikan pemutaran lagu
   - bisa melihat podcast-podcast yang tersedia
   - bisa mencari podcast yang diinginkan
   - bisa memutar podcast sesuai yang diinginkan
   - bisa berpindah antar podcast
   - bisa menghentikan pemutaran podcast
   - bisa melihat konten yang sedang populer
   - bisa melihat riwayat konten yang telah diputar
   - bisa membagikan konten
   - bisa memindai konten menggunakan barcode khas Spotify
   - bisa memperbesar dan memperkecil volume
   - bisa beralih ke akun premium
2. Use case manajemen perusahaan
   - bisa melihat data banyaknya user yang premium dan tidak premium
   - bisa melihat pendapatan per periode tertentu
   - bisa mengupdate performa aplikasi
   - bisa menambah atau mengupgrade fitur-fitur aplikasi
   - bisa melihat data konten yang banyak diminati user
3. Use case direksi perusahaan
   - bisa melihat konten yang sedang hits dan paling sering diputar oleh user
   - bisa menambahkan konten baru
   - bisa mengelola konten
   - bisa mengubah konten
   - bisa mengatur playlist
   - bisa menyediakan pemutaran lagu berdasarkan preferensi user
