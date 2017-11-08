package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    TimeEntryRepository ter;
    
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        ter = timeEntryRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = ter.find(id);
        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(ter.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = ter.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        ter.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = ter.create(timeEntry);

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }
}
