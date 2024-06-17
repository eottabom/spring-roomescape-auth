package roomescape.controller;

import java.util.List;

import roomescape.controller.dto.AvailableReservationTimeResponse;
import roomescape.controller.dto.ReservationTimeRequest;
import roomescape.controller.dto.ReservationTimeResponse;
import roomescape.service.ReservationTimeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/times")
public class ReservationTimeController {

	private final ReservationTimeService reservationTimeService;

	ReservationTimeController(ReservationTimeService reservationTimeService) {
		this.reservationTimeService = reservationTimeService;
	}

	@PostMapping
	public ResponseEntity<ReservationTimeResponse> create(@RequestBody ReservationTimeRequest request) {
		ReservationTimeRequest.validateReservationTime(request);
		return ResponseEntity.ok().body(this.reservationTimeService.create(request));
	}

	@GetMapping
	public ResponseEntity<List<ReservationTimeResponse>> getReservationTimes() {
		return ResponseEntity.ok().body(this.reservationTimeService.getReservationTimes());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") long id) {
		this.reservationTimeService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/available")
	public ResponseEntity<List<AvailableReservationTimeResponse>> getAvailableReservationTimes(
			@RequestParam("date") String date, @RequestParam("themeId") long themeId) {
		return ResponseEntity.ok(this.reservationTimeService.getAvailableReservationTimes(date, themeId));
	}

}
