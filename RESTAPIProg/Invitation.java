package hubspotFin;
import java.util.List;

public class Invitation {

	private int attendeeCount;
	private String name;
	private String startDate;
	private List<String> attendees;

	public Invitation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invitation(String name,String startDate,List<String> attendees) {
		super();
		this.attendeeCount = attendees.size();
		this.name = name;
		this.startDate = startDate;
		this.attendees = attendees;
	}

	public int getAttendeeCount() {
		return attendeeCount;
	}

	public void setAttendeeCount(int attendeeCount) {
		this.attendeeCount = attendeeCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<String> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<String> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		return "Country [attendeeCount=" + attendeeCount + ", name=" + name + ", startDate=" + startDate
				+ ", attendees=" + attendees + "]";
	}
}
