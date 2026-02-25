import { readdir, stat, writeFile } from 'node:fs/promises';
import { join, relative } from 'node:path';

const DIST_DIR = 'dist';
const BASE_URL = 'https://ggugit.pages.dev';
const OUTPUT_FILE = join(DIST_DIR, 'sitemap.xml');

const walk = async (dir) => {
  const entries = await readdir(dir, { withFileTypes: true });
  const files = [];

  for (const entry of entries) {
    const fullPath = join(dir, entry.name);
    if (entry.isDirectory()) {
      files.push(...(await walk(fullPath)));
      continue;
    }
    files.push(fullPath);
  }

  return files;
};

const toUrlPath = (filePath) => {
  const rel = relative(DIST_DIR, filePath).replaceAll('\\', '/');
  if (rel === 'index.html') return '/';
  if (rel.endsWith('/index.html')) return `/${rel.slice(0, -'index.html'.length)}`;
  if (rel.endsWith('.html')) return `/${rel.slice(0, -'.html'.length)}`;
  return null;
};

const main = async () => {
  const files = await walk(DIST_DIR);
  const pages = files.filter((path) => path.endsWith('.html'));

  const urls = await Promise.all(
    pages.map(async (filePath) => {
      const locPath = toUrlPath(filePath);
      if (!locPath) return null;
      const fileStat = await stat(filePath);
      const loc = new URL(locPath, BASE_URL).toString();
      const lastmod = fileStat.mtime.toISOString();
      return { loc, lastmod };
    })
  );

  const filtered = urls.filter(Boolean);

  const xml = `<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
${filtered
  .map(
    (item) => `  <url>
    <loc>${item.loc}</loc>
    <lastmod>${item.lastmod}</lastmod>
  </url>`
  )
  .join('\n')}
</urlset>
`;

  await writeFile(OUTPUT_FILE, xml, 'utf8');
  console.log(`Generated sitemap: ${OUTPUT_FILE} (${filtered.length} URLs)`);
};

main().catch((error) => {
  console.error('Failed to generate sitemap.xml');
  console.error(error);
  process.exit(1);
});
