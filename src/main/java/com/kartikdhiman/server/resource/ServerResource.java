package com.kartikdhiman.server.resource;

import com.kartikdhiman.server.enumeration.Status;
import com.kartikdhiman.server.model.Response;
import com.kartikdhiman.server.model.Server;
import com.kartikdhiman.server.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
	private final ServerServiceImpl serverService;

	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		return ResponseEntity.ok(
						Response.builder()
										.timeStamp(LocalDateTime.now())
										.data(Map.of("servers", serverService.list(10)))
										.message("Servers retrieved")
										.status(HttpStatus.OK)
										.statusCode(HttpStatus.OK.value())
										.build()
		);
	}

	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
		Server server = serverService.ping(ipAddress);
		return ResponseEntity.ok(
						Response.builder()
										.timeStamp(LocalDateTime.now())
										.data(Map.of("server", server))
										.message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
										.status(HttpStatus.OK)
										.statusCode(HttpStatus.OK.value())
										.build()
		);
	}

	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(
						Response.builder()
										.timeStamp(LocalDateTime.now())
										.data(Map.of("server", serverService.create(server)))
										.message("Server created")
										.status(HttpStatus.CREATED)
										.statusCode(HttpStatus.CREATED.value())
										.build()
		);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") long id) {
		return ResponseEntity.ok(
						Response.builder()
										.timeStamp(LocalDateTime.now())
										.data(Map.of("server", serverService.get(id)))
										.message("Server retrieved")
										.status(HttpStatus.OK)
										.statusCode(HttpStatus.OK.value())
										.build()
		);
	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(
						Response.builder()
										.timeStamp(LocalDateTime.now())
										.data(Map.of("server", serverService.update(server)))
										.message("Server updated")
										.status(HttpStatus.OK)
										.statusCode(HttpStatus.OK.value())
										.build()
		);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") long id) {
		return ResponseEntity.ok(
						Response.builder()
										.timeStamp(LocalDateTime.now())
										.data(Map.of("isDeleted", serverService.delete(id)))
										.message("Server deleted")
										.status(HttpStatus.OK)
										.statusCode(HttpStatus.OK.value())
										.build()
		);
	}

	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/" + fileName));
	}
}
