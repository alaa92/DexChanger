package huluwa.dexparser.format;

import huluwa.dexparser.Exception.NonStandardLeb128Exception;
import huluwa.dexparser.Exception.QueryNextDataException;
import huluwa.dexparser.base.Item;
import huluwa.dexparser.type.Leb128;

public class encoded_field extends Item {
	public static final String itemName = "encoded_field";
	Leb128 field_id;
	int real_id;
	Leb128 access_flags;

	public encoded_field(byte[] data, int off) {
		super(data, off);
	}

	@Override
	public int getLength() {
		return field_id.getLength() + access_flags.getLength();
	}

	@Override
	public String getName() {
		return itemName;
	}

	@Override
	public void parseData() throws QueryNextDataException {
		try {
			this.field_id = this.cursor.nextLeb128();
			this.access_flags = this.cursor.nextLeb128();
		} catch (NonStandardLeb128Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[*E]" + getName() + ":" + e.getMessage());
		}
	}

}
