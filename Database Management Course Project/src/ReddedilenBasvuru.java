/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ReddedilenBasvuru {
    
    private int BasvuruNumarasi;
    private int BasvuruSureciTypeID;
    private int RedSebebi;
    
    public ReddedilenBasvuru() {
    }

    public ReddedilenBasvuru(int BasvuruNumarasi, int BasvuruSureciTypeID, int RedSebebi) {
        this.BasvuruNumarasi = BasvuruNumarasi;
        this.BasvuruSureciTypeID = BasvuruSureciTypeID;
        this.RedSebebi = RedSebebi;
    }

    public int getBasvuruNumarasi() {
        return BasvuruNumarasi;
    }

    public int getBasvuruSureciTypeID() {
        return BasvuruSureciTypeID;
    }

    public int getDevamSebebi() {
        return RedSebebi;
    }

    @Override
    public String toString() {
        return "DevamEdenBasvuru{" + "BasvuruNumarasi=" + BasvuruNumarasi + ", BasvuruSureciTypeID=" + BasvuruSureciTypeID + ", RedSebebi=" + RedSebebi + '}';
    }
    
    
    
    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "BasvuruSureciTypeID": return BasvuruSureciTypeID;
		case "BasvuruNumarasi": return BasvuruNumarasi;
                case "RedSebebi": return RedSebebi;
                default: return null;
	}
    }
    
}