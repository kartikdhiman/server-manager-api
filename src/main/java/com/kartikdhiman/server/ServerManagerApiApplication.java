package com.kartikdhiman.server;

import com.kartikdhiman.server.enumeration.Status;
import com.kartikdhiman.server.model.Server;
import com.kartikdhiman.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerManagerApiApplication {
	private static final String imageUrl = "http://localhost:8080/server";

	public static void main(String[] args) {
		SpringApplication.run(ServerManagerApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", imageUrl + "/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.161", "Kali Linux", "32 GB", "Hacking Server", imageUrl + "/server2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.162", "Fedora Linux", "64 GB", "Lab PC", imageUrl + "/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.163 ", "Mac OS", "128 GB", "Professional Server", imageUrl + "/server4.png", Status.SERVER_DOWN));
		};
	}

}
